/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.nuts.runtime.standalone.repository.impl;

import net.thevpc.nuts.*;
import net.thevpc.nuts.io.NutsCp;
import net.thevpc.nuts.io.NutsPath;
import net.thevpc.nuts.io.NutsPathOption;
import net.thevpc.nuts.runtime.standalone.event.DefaultNutsContentEvent;
import net.thevpc.nuts.runtime.standalone.id.filter.NutsSearchIdByDescriptor;
import net.thevpc.nuts.runtime.standalone.id.util.NutsIdUtils;
import net.thevpc.nuts.runtime.standalone.repository.NutsRepositoryHelper;
import net.thevpc.nuts.runtime.standalone.repository.cmd.NutsRepositorySupportedAction;
import net.thevpc.nuts.runtime.standalone.session.NutsSessionUtils;
import net.thevpc.nuts.runtime.standalone.util.iter.IteratorBuilder;
import net.thevpc.nuts.runtime.standalone.util.iter.IteratorUtils;
import net.thevpc.nuts.runtime.standalone.workspace.NutsWorkspaceExt;
import net.thevpc.nuts.runtime.standalone.workspace.NutsWorkspaceUtils;
import net.thevpc.nuts.spi.NutsDeployRepositoryCommand;
import net.thevpc.nuts.spi.NutsPushRepositoryCommand;
import net.thevpc.nuts.spi.NutsRepositorySPI;
import net.thevpc.nuts.util.NutsIterator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author thevpc
 */
public class NutsRepositoryMirroringHelper {

    private final NutsRepository repo;
    protected NutsRepositoryFolderHelper cache;

    public NutsRepositoryMirroringHelper(NutsRepository repo, NutsRepositoryFolderHelper cache) {
        this.repo = repo;
        this.cache = cache;
    }

    protected NutsIterator<NutsId> searchVersionsImpl_appendMirrors(NutsIterator<NutsId> namedNutIdIterator, NutsId id, NutsIdFilter idFilter, NutsFetchMode fetchMode, NutsSession session) {
        if (!session.isTransitive()) {
            return namedNutIdIterator;
        }
        List<NutsIterator<? extends NutsId>> list = new ArrayList<>();
        list.add(namedNutIdIterator);
        if (repo.config().setSession(session).isSupportedMirroring()) {
            for (NutsRepository repo : repo.config().setSession(session).getMirrors()) {
                NutsSpeedQualifier sup = NutsSpeedQualifier.UNAVAILABLE;
                try {
                    sup = NutsRepositoryHelper.getSupportSpeedLevel(repo, NutsRepositorySupportedAction.SEARCH, id, fetchMode, session.isTransitive(), session);
                } catch (Exception ex) {
                    //                errors.append(CoreStringUtils.exceptionToString(ex)).append("\n");
                }
                if (sup != NutsSpeedQualifier.UNAVAILABLE) {
                    NutsRepositorySPI repoSPI = NutsWorkspaceUtils.of(session).repoSPI(repo);
                    list.add(
                            IteratorBuilder.of(repoSPI.searchVersions().setId(id).setFilter(idFilter).setSession(session)
                                            .setFetchMode(fetchMode)
                                            .getResult(), session)
                                    .named("searchInMirror(" + repo.getName() + ")")
                                    .safeIgnore()
                                    .build()
                    );
                }
            }
        }
        return IteratorUtils.concat(list);
    }

    protected NutsPath fetchContent(NutsId id, NutsDescriptor descriptor, String localPath, NutsFetchMode fetchMode, NutsSession session) {
        NutsPath cacheContent = cache.getLongIdLocalFile(id, session);
        NutsRepositoryConfigManager rconfig = repo.config().setSession(session);
        if (session.isTransitive() && rconfig.isSupportedMirroring()) {
            for (NutsRepository mirror : rconfig.setSession(session).getMirrors()) {
                try {
                    NutsRepositorySPI repoSPI = NutsWorkspaceUtils.of(session).repoSPI(mirror);
                    NutsPath c = repoSPI.fetchContent().setId(id).setDescriptor(descriptor).setLocalPath(cacheContent.toString()).setSession(session)
                            .setFetchMode(fetchMode)
                            .getResult();
                    if (c != null) {
                        if (localPath != null) {
                            NutsCp.of(session)
                                    .from(c).to(NutsPath.of(localPath,session)).addOptions(NutsPathOption.SAFE).run();
                        } else {
                            return c;
                        }
                        return c;
                    }
                } catch (NutsNotFoundException ex) {
                    //ignore!
                }
            }
        }
        return null;
    }

    public NutsWorkspace getWorkspace() {
        return repo.getWorkspace();
    }

    protected String getIdFilename(NutsId id, NutsSession session) {
        return NutsRepositoryExt.of(repo).getIdFilename(id, session);
    }

    protected NutsDescriptor fetchDescriptorImplInMirrors(NutsId id, NutsFetchMode fetchMode, NutsSession session) {
        String idFilename = getIdFilename(id, session);
        NutsPath versionFolder = cache.getLongIdLocalFolder(id, session);
        NutsRepositoryConfigManager rconf = repo.config().setSession(session);
        if (session.isTransitive() && rconf.isSupportedMirroring()) {
            for (NutsRepository remote : rconf.getMirrors()) {
                NutsDescriptor nutsDescriptor = null;
                try {
                    NutsRepositorySPI repoSPI = NutsWorkspaceUtils.of(session).repoSPI(remote);
                    nutsDescriptor = repoSPI.fetchDescriptor().setId(id).setSession(session).setFetchMode(fetchMode).getResult();
                } catch (Exception ex) {
                    //ignore
                }
                if (nutsDescriptor != null) {
                    NutsPath goodFile = null;
                    goodFile = versionFolder.resolve(idFilename);
//                    String a = nutsDescriptor.getAlternative();
//                    if (CoreNutsUtils.isDefaultAlternative(a)) {
//                        goodFile = versionFolder.resolve(idFilename);
//                    } else {
//                        goodFile = versionFolder.resolve(NutsUtilStrings.trim(a)).resolve(idFilename);
//                    }
                    nutsDescriptor.formatter(session).print(goodFile);
                    return nutsDescriptor;
                }
            }
        }
        return null;
    }

    public NutsIterator<NutsId> search(NutsIterator<NutsId> li, NutsIdFilter filter, NutsFetchMode fetchMode, NutsSession session) {
        NutsRepositoryConfigManager rconfig = repo.config().setSession(session);
        if (!session.isTransitive() || !rconfig.isSupportedMirroring()) {
            return li;
        }
        List<NutsIterator<? extends NutsId>> all = new ArrayList<>();
        all.add(li);
        for (NutsRepository remote : rconfig.setSession(session).getMirrors()) {
            NutsRepositorySPI repoSPI = NutsWorkspaceUtils.of(session).repoSPI(remote);
            all.add(IteratorUtils.safeIgnore(
                    repoSPI.search().setFilter(filter).setSession(session).setFetchMode(fetchMode).getResult(), session
            ));
        }
        return IteratorUtils.concat(all);

    }

    public void push(NutsPushRepositoryCommand cmd) {
        NutsSession session = cmd.getSession();
        NutsSessionUtils.checkSession(getWorkspace(), session);
        NutsId id = cmd.getId();
        String repository = cmd.getRepository();
        NutsSession nonTransitiveSession = session.copy().setTransitive(false);
        NutsRepositorySPI repoSPI = NutsWorkspaceUtils.of(session).repoSPI(repo);
        NutsDescriptor desc = repoSPI.fetchDescriptor().setId(id).setSession(nonTransitiveSession).setFetchMode(NutsFetchMode.LOCAL).getResult();
        NutsPath local = repoSPI.fetchContent().setId(id).setSession(nonTransitiveSession).setFetchMode(NutsFetchMode.LOCAL).getResult();
        if (local == null) {
            throw new NutsNotFoundException(session, id);
        }
        if (!repo.config().setSession(session).isSupportedMirroring()) {
            throw new NutsPushException(session, id, NutsMessage.ofCstyle("unable to push %s. no repository found.", id == null ? "<null>" : id));
        }
        NutsRepository repo = this.repo;
        if (NutsBlankable.isBlank(repository)) {
            List<NutsRepository> all = new ArrayList<>();
            for (NutsRepository remote : repo.config().setSession(session).getMirrors()) {
                NutsSpeedQualifier lvl = NutsRepositoryHelper.getSupportSpeedLevel(remote, NutsRepositorySupportedAction.DEPLOY, id, NutsFetchMode.LOCAL, false, session);
                if (lvl != NutsSpeedQualifier.UNAVAILABLE) {
                    all.add(remote);
                }
            }
            if (all.isEmpty()) {
                throw new NutsPushException(session, id, NutsMessage.ofCstyle("unable to push %s. no repository found.", id == null ? "<null>" : id));
            } else if (all.size() > 1) {
                throw new NutsPushException(session, id,
                        NutsMessage.ofCstyle("unable to perform push for %s. at least two Repositories (%s) provides the same nuts %s",
                                id,
                                all.stream().map(NutsRepository::getName).collect(Collectors.joining(",")),
                                id
                        )
                );
            }
            repo = all.get(0);
        } else {
            repo = this.repo.config().setSession(session.copy().setTransitive(false)).getMirror(repository);
        }
        if (repo != null) {
            NutsId effId = NutsIdUtils.createContentFaceId(id.builder().setPropertiesQuery("").build(), desc,session)
//                    .setAlternative(NutsUtilStrings.trim(desc.getAlternative()))
                    ;
            NutsDeployRepositoryCommand dep = repoSPI.deploy()
                    .setId(effId)
                    .setContent(local)
                    .setDescriptor(desc)
//                    .setOffline(cmd.isOffline())
                    //.setFetchMode(NutsFetchMode.LOCAL)
                    .setSession(session)
                    .run();
            NutsRepositoryHelper.of(repo).events().fireOnPush(new DefaultNutsContentEvent(
                    local, dep, session, repo));
        } else {
            throw new NutsRepositoryNotFoundException(session, repository);
        }
    }

    public NutsId searchLatestVersion(NutsId bestId, NutsId id, NutsIdFilter filter, NutsFetchMode fetchMode, NutsSession session) {
        NutsRepositoryConfigManager rconfig = repo.config().setSession(session);
        if (session.isTransitive() && rconfig.isSupportedMirroring()) {
            for (NutsRepository remote : rconfig.setSession(session).getMirrors()) {
                NutsDescriptor nutsDescriptor = null;
                try {
                    NutsRepositorySPI repoSPI = NutsWorkspaceUtils.of(session).repoSPI(remote);
                    nutsDescriptor = repoSPI.fetchDescriptor().setId(id).setSession(session).setFetchMode(fetchMode).getResult();
                } catch (Exception ex) {
                    //ignore
                }
                if (nutsDescriptor != null) {
                    if (filter == null || filter.acceptSearchId(new NutsSearchIdByDescriptor(nutsDescriptor), session)) {
//                        NutsId id2 = C                                oreNutsUtils.createComponentFaceId(getWorkspace().resolveEffectiveId(nutsDescriptor,session),nutsDescriptor,null);
                        NutsWorkspaceExt dws = NutsWorkspaceExt.of(getWorkspace());
                        NutsId id2 = dws.resolveEffectiveId(nutsDescriptor, session).builder().setFaceDescriptor().build();
                        NutsPath localNutFile = cache.getLongIdLocalFile(id2, session);
                        nutsDescriptor.formatter(session).print(localNutFile);
                        if (bestId == null || id2.getVersion().compareTo(bestId.getVersion()) > 0) {
                            bestId = id2;
                        }
                    }
                }
            }
        }
        return bestId;
    }

}
