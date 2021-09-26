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

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Base Boot Nuts Exception. Thrown when the Workspace could is booting
 * and is not yet available.
 *
 * @author thevpc
 * @app.category Exceptions
 * @since 0.5.4
 */
public class NutsBootException extends RuntimeException {

    private final int exitCode;
    private final NutsMessage message;

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public NutsBootException(NutsMessage message) {
        super(message.toString());
        this.message = message;
        this.exitCode = 1;
    }

    /**
     * Constructs a new runtime exception with the specified detail message and
     * cause.
     * <br>
     * Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A {@code null} value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     */
    public NutsBootException(NutsMessage message, Throwable cause) {
        super(message.toString(), cause);
        this.message = message;
        this.exitCode = 1;
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param exitCode exit code
     * @param message  the detail message. The detail message is saved for
     *                 later retrieval by the {@link #getMessage()} method.
     */
    public NutsBootException(NutsMessage message, int exitCode) {
        super(message == null ? "" : message.toString());
        this.message = message;
        this.exitCode = exitCode;
    }

    /**
     * Constructs a new runtime exception with the specified detail message and
     * cause.
     * <br>
     * Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     * @param exitCode exit code
     * @param message  the detail message (which is saved for later retrieval
     *                 by the {@link #getMessage()} method).
     * @param cause    the cause (which is saved for later retrieval by the
     *                 {@link #getCause()} method).  (A {@code null} value is
     *                 permitted, and indicates that the cause is nonexistent or
     *                 unknown.)
     */
    public NutsBootException(NutsMessage message, Throwable cause, int exitCode) {
        super(message == null ? "" : message.toString(), cause);
        this.message = message;
        this.exitCode = exitCode;
    }

    public static NutsBootException detectBootException(Throwable th) {
        Set<Throwable> visited = new HashSet<>();
        Stack<Throwable> stack = new Stack<>();
        if (th != null) {
            stack.push(th);
        }
        while (!stack.isEmpty()) {
            Throwable a = stack.pop();
            if (visited.add(a)) {
                if (th instanceof NutsBootException) {
                    return ((NutsBootException) th);
                }
                Throwable c = th.getCause();
                if (c != null) {
                    stack.add(c);
                }
            }
        }
        return null;
    }

    public int getExitCode() {
        return exitCode;
    }

    public NutsMessage getFormattedMessage() {
        return message;
    }
}
