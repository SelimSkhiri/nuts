/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.vpc.app.nuts.core.test.blackbox;

import net.vpc.app.nuts.NutsOsFamily;
import net.vpc.app.nuts.core.test.utils.TestUtils;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.vpc.app.nuts.Nuts;
import net.vpc.app.nuts.NutsId;
import net.vpc.app.nuts.NutsWorkspace;
import net.vpc.app.nuts.runtime.util.io.CoreIOUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author vpc
 */
public class Test09_FindLinuxTest {

    private static String baseFolder;

    @Test
    public void find() throws Exception {
        Map<String, String> extraProperties = new HashMap<>();
        extraProperties.put("nuts.export.always-show-command", "true");
        TestUtils.setSystemProperties(extraProperties);

        //should throw NutsNotFoundException because
        //would not be able to installe nsh and other companions
        NutsWorkspace ws = Nuts.openWorkspace(new String[]{
            "--workspace", baseFolder + "/" + TestUtils.getCallerMethodName(),
            "--archetype", "default",
            "--yes",
            "--skip-companions"
        });

        List<NutsId> def = ws.search().id("nuts").optional(false).latest().failFast(false)
//                .repository("maven-local")
                .defaultVersions()
                .installed()
                .getResultIds().list();
                
        System.out.println(def);
    }

    @BeforeClass
    public static void setUpClass() throws IOException {
        baseFolder = new File("./runtime/test/" + TestUtils.getCallerClassSimpleName()).getCanonicalFile().getPath();
        CoreIOUtils.delete(null,new File(baseFolder));
    }

    @AfterClass
    public static void tearUpClass() throws IOException {
        CoreIOUtils.delete(null,new File(baseFolder));
    }

    @Before
    public void startup() throws IOException {
        Assume.assumeTrue(Nuts.getPlatformOsFamily()== NutsOsFamily.LINUX);
        TestUtils.unsetNutsSystemProperties();
    }

    @After
    public void cleanup() {
        TestUtils.unsetNutsSystemProperties();
    }

}
