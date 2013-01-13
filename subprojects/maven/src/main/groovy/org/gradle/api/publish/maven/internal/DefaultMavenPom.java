/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.api.publish.maven.internal;

import org.gradle.api.Action;
import org.gradle.api.artifacts.Dependency;
import org.gradle.api.internal.UserCodeAction;
import org.gradle.api.XmlProvider;
import org.gradle.listener.ActionBroadcast;

import java.util.Set;

public class DefaultMavenPom implements MavenPomInternal {

    private final ActionBroadcast<XmlProvider> xmlAction = new ActionBroadcast<XmlProvider>();
    private final MavenPublicationInternal mavenPublication;

    public DefaultMavenPom(MavenPublicationInternal mavenPublication) {
        this.mavenPublication = mavenPublication;
    }

    public void withXml(Action<? super XmlProvider> action) {
        xmlAction.add(new UserCodeAction<XmlProvider>("Could not apply withXml() to generated POM", action));
    }

    public Action<XmlProvider> getXmlAction() {
        return xmlAction;
    }

    public MavenProjectIdentity getProjectIdentity() {
        return mavenPublication.asNormalisedPublication();
    }

    public Set<Dependency> getRuntimeDependencies() {
        return mavenPublication.getRuntimeDependencies();
    }
}
