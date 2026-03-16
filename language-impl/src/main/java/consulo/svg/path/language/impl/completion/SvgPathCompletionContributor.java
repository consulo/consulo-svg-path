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

package consulo.svg.path.language.impl.completion;

import consulo.annotation.component.ExtensionImpl;
import consulo.svg.path.language.SvgPathLanguage;
import consulo.svg.path.language.SvgPathTokenSets;
import consulo.svg.path.language.psi.SvgPathTypes;
import consulo.language.Language;
import consulo.language.ast.IElementType;
import consulo.language.editor.completion.CompletionContributor;
import consulo.language.editor.completion.CompletionParameters;
import consulo.language.editor.completion.CompletionResultSet;
import consulo.language.editor.completion.lookup.LookupElementBuilder;
import consulo.language.impl.ast.TreeUtil;
import consulo.language.impl.parser.GeneratedParserUtilBase;
import consulo.language.psi.PsiFile;
import consulo.language.psi.PsiFileFactory;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.*;

@ExtensionImpl
public class SvgPathCompletionContributor extends CompletionContributor {
    private static final Map<IElementType, String[]> COMMAND_LETTERS = new LinkedHashMap<>();

    static {
        COMMAND_LETTERS.put(SvgPathTypes.MOVE_TO, new String[]{"M", "m"});
        COMMAND_LETTERS.put(SvgPathTypes.LINE_TO, new String[]{"L", "l"});
        COMMAND_LETTERS.put(SvgPathTypes.HORIZONTAL_LINE_TO, new String[]{"H", "h"});
        COMMAND_LETTERS.put(SvgPathTypes.VERTICAL_LINE_TO, new String[]{"V", "v"});
        COMMAND_LETTERS.put(SvgPathTypes.CURVE_TO, new String[]{"C", "c"});
        COMMAND_LETTERS.put(SvgPathTypes.SMOOTH_CURVE_TO, new String[]{"S", "s"});
        COMMAND_LETTERS.put(SvgPathTypes.QUADRATIC_TO, new String[]{"Q", "q"});
        COMMAND_LETTERS.put(SvgPathTypes.SMOOTH_QUADRATIC_TO, new String[]{"T", "t"});
        COMMAND_LETTERS.put(SvgPathTypes.ARC_TO, new String[]{"A", "a"});
        COMMAND_LETTERS.put(SvgPathTypes.CLOSE_PATH, new String[]{"Z", "z"});
    }

    @Override
    public void fillCompletionVariants(@Nonnull CompletionParameters parameters, @Nonnull CompletionResultSet result) {
        Set<IElementType> commandTypes = suggestCommandTypes(parameters);
        for (IElementType type : commandTypes) {
            String[] letters = COMMAND_LETTERS.get(type);
            if (letters != null) {
                for (String letter : letters) {
                    result.addElement(LookupElementBuilder.create(letter).bold());
                }
            }
        }
    }

    @Nonnull
    private static Set<IElementType> suggestCommandTypes(@Nonnull CompletionParameters parameters) {
        PsiFile posFile = parameters.getOriginalFile();
        int completionOffset = parameters.getOffset();
        CharSequence text = posFile.getText();

        Set<IElementType> types = new LinkedHashSet<>();

        GeneratedParserUtilBase.CompletionState state = new GeneratedParserUtilBase.CompletionState(completionOffset) {
            @Nullable
            @Override
            public String convertItem(Object o) {
                if (o instanceof IElementType && SvgPathTokenSets.COMMANDS.contains((IElementType) o)) {
                    types.add((IElementType) o);
                }
                return null;
            }
        };

        PsiFile file = PsiFileFactory.getInstance(posFile.getProject())
                .createFileFromText("a.svgpath", SvgPathLanguage.INSTANCE, text, true, false);
        file.putUserData(GeneratedParserUtilBase.COMPLETION_STATE_KEY, state);
        TreeUtil.ensureParsed(file.getNode());

        return types;
    }

    @Nonnull
    @Override
    public Language getLanguage() {
        return SvgPathLanguage.INSTANCE;
    }
}
