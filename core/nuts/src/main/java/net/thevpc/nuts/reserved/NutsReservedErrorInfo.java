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
package net.thevpc.nuts.reserved;

import net.thevpc.nuts.NutsBlankable;
import net.thevpc.nuts.NutsId;

public class NutsReservedErrorInfo {
    private final NutsId nutsId;
    private final String repository;
    private final String url;
    private final String message;
    private final Throwable throwable;

    public NutsReservedErrorInfo(NutsId nutsId, String repository, String url, String message, Throwable throwable) {
        this.nutsId = nutsId;
        this.repository = repository;
        this.url = url;
        this.message = message;
        this.throwable = throwable;
    }

    public NutsId getNutsId() {
        return nutsId;
    }

    public String getRepository() {
        return repository;
    }

    public String getUrl() {
        return url;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        if(!NutsBlankable.isBlank(getMessage())){
            sb.append(getMessage());
        }else{
            sb.append("unexpected error");
        }
        if(getNutsId()!=null){
            if(sb.length()>0){
               sb.append(" ");
            }
            sb.append(" for id ").append(getNutsId());
        }
        if(getUrl()!=null){
            if(sb.length()>0){
               sb.append(" ");
            }
            sb.append(" from ").append(getUrl());
        }
        if(getRepository()!=null){
            if(sb.length()>0){
               sb.append(" ");
            }
            sb.append(" for repository ").append(getRepository());
        }
        if(getThrowable()!=null){
            if(sb.length()>0){
               sb.append(" ");
            }
            sb.append(": ").append(getThrowable().toString());
        }
        return sb.toString();
    }
}
