/**
 * ====================================================================
 * Nuts : Network Updatable Things Service
 * (universal package manager)
 * <br>
 * is a new Open Source Package Manager to help install packages
 * and libraries for runtime execution. Nuts is the ultimate companion for
 * maven (and other build managers) as it helps installing all package
 * dependencies at runtime. Nuts is not tied to java and is a good choice
 * to share shell scripts and other 'things' . Its based on an extensible
 * architecture to help supporting a large range of sub managers / repositories.
 *
 * <br>
 * <p>
 * Copyright [2020] [thevpc]
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain a
 * copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 * <br>
 * ====================================================================
 */
package net.thevpc.nuts;

import java.util.ArrayList;
import java.util.List;

public class NutsLauncherOptions implements Cloneable {
    private boolean createAlias;
    private boolean createScript;
    private NutsSupportMode createMenuLauncher;
    private NutsSupportMode createDesktopLauncher;
    private NutsSupportMode createUserLauncher;
    private boolean installExtensions;
    private String alias;
    private String shortcutName;
    private String customShortcutPath;
    private String customScriptPath;
    private String icon;
    private String menuCategory;
    private boolean openTerminal;

    private Boolean switchWorkspace;
    private NutsId id;
    private List<String> args = new ArrayList<>();

    private List<String> nutsOptions = new ArrayList<>();

    private String switchWorkspaceLocation;
    private String workingDirectory;

    public boolean isCreateScript() {
        return createScript;
    }

    public NutsLauncherOptions setCreateScript(boolean createScript) {
        this.createScript = createScript;
        return this;
    }

    public NutsSupportMode getCreateMenuLauncher() {
        return createMenuLauncher;
    }

    public NutsLauncherOptions setCreateMenuLauncher(NutsSupportMode createMenuShortcut) {
        this.createMenuLauncher = createMenuShortcut;
        return this;
    }

    public NutsSupportMode getCreateDesktopLauncher() {
        return createDesktopLauncher;
    }

    public NutsLauncherOptions setCreateDesktopLauncher(NutsSupportMode createDesktopLauncher) {
        this.createDesktopLauncher = createDesktopLauncher;
        return this;
    }

    public NutsSupportMode getCreateUserLauncher() {
        return createUserLauncher;
    }

    public NutsLauncherOptions setCreateUserLauncher(NutsSupportMode createUserLauncher) {
        this.createUserLauncher = createUserLauncher;
        return this;
    }

    public String getShortcutName() {
        return shortcutName;
    }

    public NutsLauncherOptions setShortcutName(String shortcutName) {
        this.shortcutName = shortcutName;
        return this;
    }

    public String getCustomShortcutPath() {
        return customShortcutPath;
    }

    public NutsLauncherOptions setCustomShortcutPath(String customShortcutPath) {
        this.customShortcutPath = customShortcutPath;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public NutsLauncherOptions setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public String getMenuCategory() {
        return menuCategory;
    }

    public NutsLauncherOptions setMenuCategory(String menuCategory) {
        this.menuCategory = menuCategory;
        return this;
    }

    public boolean isOpenTerminal() {
        return openTerminal;
    }

    public NutsLauncherOptions setOpenTerminal(boolean openTerminal) {
        this.openTerminal = openTerminal;
        return this;
    }

    public NutsId getId() {
        return id;
    }

    public NutsLauncherOptions setId(NutsId id) {
        this.id = id;
        return this;
    }

    public List<String> getArgs() {
        return args;
    }

    public NutsLauncherOptions setArgs(List<String> args) {
        this.args = args;
        return this;
    }

    public List<String> getNutsOptions() {
        return nutsOptions;
    }

    public NutsLauncherOptions setNutsOptions(List<String> nutsOptions) {
        this.nutsOptions = nutsOptions;
        return this;
    }

    public String getAlias() {
        return alias;
    }

    public NutsLauncherOptions setAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public boolean isCreateAlias() {
        return createAlias;
    }

    public NutsLauncherOptions setCreateAlias(boolean createAlias) {
        this.createAlias = createAlias;
        return this;
    }

    public boolean isInstallExtensions() {
        return installExtensions;
    }

    public NutsLauncherOptions setInstallExtensions(boolean installExtensions) {
        this.installExtensions = installExtensions;
        return this;
    }

    public String getCustomScriptPath() {
        return customScriptPath;
    }

    public NutsLauncherOptions setCustomScriptPath(String customScriptPath) {
        this.customScriptPath = customScriptPath;
        return this;
    }

    public Boolean getSwitchWorkspace() {
        return switchWorkspace;
    }

    public NutsLauncherOptions setSwitchWorkspace(Boolean switchWorkspace) {
        this.switchWorkspace = switchWorkspace;
        return this;
    }

    public String getSwitchWorkspaceLocation() {
        return switchWorkspaceLocation;
    }

    public NutsLauncherOptions setSwitchWorkspaceLocation(String switchWorkspaceLocation) {
        this.switchWorkspaceLocation = switchWorkspaceLocation;
        return this;
    }

    public String getWorkingDirectory() {
        return workingDirectory;
    }

    public NutsLauncherOptions setWorkingDirectory(String workingDirectory) {
        this.workingDirectory = workingDirectory;
        return this;
    }

    public NutsLauncherOptions copy() {
        try {
            NutsLauncherOptions c = (NutsLauncherOptions) super.clone();
            if (c.args != null) {
                c.args = new ArrayList<>(c.args);
            }
            if (c.nutsOptions != null) {
                c.nutsOptions = new ArrayList<>(c.nutsOptions);
            }
            return c;
        } catch (CloneNotSupportedException e) {
            throw new UnsupportedOperationException(e);
        }
    }
}
