/*
 * Copyright 2013-2026 consulo.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package consulo.svg.path.svg.inject;

import consulo.annotation.access.RequiredReadAction;
import consulo.annotation.component.ExtensionImpl;
import consulo.images.svg.SVGLanguage;
import consulo.language.inject.MultiHostInjector;
import consulo.language.inject.MultiHostRegistrar;
import consulo.language.psi.ElementManipulators;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiLanguageInjectionHost;
import consulo.svg.path.language.SvgPathLanguage;
import consulo.xml.language.psi.XmlAttribute;
import consulo.xml.language.psi.XmlAttributeValue;
import consulo.xml.language.psi.XmlTag;
import jakarta.annotation.Nonnull;

@ExtensionImpl
public class SvgPathLanguageInjector implements MultiHostInjector {
    @Nonnull
    @Override
    public Class<? extends PsiElement> getElementClass() {
        return XmlAttributeValue.class;
    }

    @RequiredReadAction
    @Override
    public void injectLanguages(@Nonnull MultiHostRegistrar registrar, @Nonnull PsiElement context) {
        if (!(context instanceof XmlAttributeValue attributeValue)) {
            return;
        }

        if (!attributeValue.getContainingFile().getLanguage().is(SVGLanguage.INSTANCE)) {
            return;
        }

        PsiElement parent = attributeValue.getParent();
        if (!(parent instanceof XmlAttribute attribute)) {
            return;
        }

        if (!"d".equals(attribute.getName())) {
            return;
        }

        PsiElement grandParent = attribute.getParent();
        if (!(grandParent instanceof XmlTag tag)) {
            return;
        }

        if (!"path".equals(tag.getLocalName())) {
            return;
        }

        registrar.startInjecting(SvgPathLanguage.INSTANCE)
            .addPlace(null, null, (PsiLanguageInjectionHost) attributeValue, ElementManipulators.getValueTextRange(attributeValue))
            .doneInjecting();
    }
}
