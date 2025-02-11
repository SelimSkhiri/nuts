/**
 * ====================================================================
 *             Doovos (Distributed Object Oriented Operating System)
 *
 * Doovos is a new Open Source Distributed Object Oriented Operating System
 * Design and implementation based on the Java Platform.
 * Actually, it is a try for designing a distributed operation system in
 * top of existing centralized/network OS.
 * Designed OS will follow the object oriented architecture for redefining
 * all OS resources (memory,process,file system,device,...etc.) in a highly
 * distributed context.
 * Doovos is also a distributed Java virtual machine that implements JVM
 * specification on top the distributed resources context.
 *
 * Doovos BIN is a standard implementation for Doovos boot sequence, shell and
 * common application tools. These applications are running onDoovos guest JVM
 * (distributed jvm).
 * <br>
 *
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
package net.thevpc.nuts.toolbox.nsh.jshell;

import net.thevpc.nuts.NutsMessage;
import net.thevpc.nuts.NutsSession;

/**
 * @author thevpc (taha.bensalah@gmail.com)
 * %lastmodified 04-dec.-2005
 * Time: 17:53:11
 */
public class JShellQuitException extends JShellException {

    public JShellQuitException(NutsSession session,int result) {
        this(session, null, result);
    }
    
    public JShellQuitException(NutsSession session) {
        this(session, null, 0);
    }

    public JShellQuitException(NutsSession session, Throwable cause, int result) {
        super(session, NutsMessage.ofPlain("quit"), cause,result);
    }
    
}