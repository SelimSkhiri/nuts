/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.nuts.core.test;

import java.io.ByteArrayOutputStream;

import net.thevpc.nuts.*;

import java.io.InputStream;

import net.thevpc.nuts.io.NutsCp;
import net.thevpc.nuts.io.NutsPathOption;
import net.thevpc.nuts.runtime.standalone.io.util.CoreIOUtils;
import org.junit.jupiter.api.*;
import net.thevpc.nuts.core.test.utils.*;

/**
 *
 * @author thevpc
 */
public class Test22_CpTest {
    static NutsSession session;

    @BeforeAll
    public static void init() {
        session = TestUtils.openNewMinTestWorkspace();
    }

    @Test
    public void minimal1() throws Exception {
        final String url = "https://repo.maven.apache.org/maven2/archetype-catalog.xml";
        InputStream j1 = CoreIOUtils.getCachedUrlWithSHA1(url, "archetype-catalog", true,session);
        //just to consume the stream
        NutsCp.of(session).from(j1).to(new ByteArrayOutputStream()).addOptions(NutsPathOption.LOG, NutsPathOption.TRACE).run();
        TestUtils.println(j1);
        InputStream j2 = CoreIOUtils.getCachedUrlWithSHA1(url, "archetype-catalog", true,session);
        //just to consume the stream
        NutsCp.of(session).from(j2).to(new ByteArrayOutputStream()).addOptions(NutsPathOption.LOG, NutsPathOption.TRACE).run();
        TestUtils.println(j2);
    }

}
