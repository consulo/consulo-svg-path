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

package consulo.svg.path.language.impl.highlighting;

import consulo.annotation.component.ExtensionImpl;
import consulo.codeEditor.HighlighterColors;
import consulo.colorScheme.TextAttributesKey;
import consulo.svg.path.language.SvgPathLanguage;
import consulo.svg.path.language.impl.SvgPathLexer;
import consulo.svg.path.language.psi.SvgPathTypes;
import consulo.language.Language;
import consulo.language.ast.IElementType;
import consulo.language.ast.TokenType;
import consulo.language.editor.highlight.SyntaxHighlighter;
import consulo.language.editor.highlight.SyntaxHighlighterBase;
import consulo.language.editor.highlight.SyntaxHighlighterFactory;
import consulo.language.lexer.Lexer;
import consulo.project.Project;
import consulo.virtualFileSystem.VirtualFile;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

import static consulo.codeEditor.DefaultLanguageHighlighterColors.*;

@ExtensionImpl
public class SvgPathSyntaxHighlighterFactory extends SyntaxHighlighterFactory {
    @Nonnull
    @Override
    public SyntaxHighlighter getSyntaxHighlighter(@Nullable Project project, @Nullable VirtualFile virtualFile) {
        return new SvgPathSyntaxHighlighter();
    }

    @Nonnull
    @Override
    public Language getLanguage() {
        return SvgPathLanguage.INSTANCE;
    }

    private static class SvgPathSyntaxHighlighter extends SyntaxHighlighterBase {
        private static final Map<IElementType, TextAttributesKey> ourAttributes = new HashMap<>();

        static {
            fillMap(ourAttributes, KEYWORD,
                    SvgPathTypes.MOVE_TO, SvgPathTypes.LINE_TO, SvgPathTypes.HORIZONTAL_LINE_TO,
                    SvgPathTypes.VERTICAL_LINE_TO, SvgPathTypes.CURVE_TO, SvgPathTypes.SMOOTH_CURVE_TO,
                    SvgPathTypes.QUADRATIC_TO, SvgPathTypes.SMOOTH_QUADRATIC_TO, SvgPathTypes.ARC_TO,
                    SvgPathTypes.CLOSE_PATH);
            fillMap(ourAttributes, NUMBER, SvgPathTypes.NUMBER);
            fillMap(ourAttributes, COMMA, SvgPathTypes.COMMA);
            fillMap(ourAttributes, HighlighterColors.BAD_CHARACTER, TokenType.BAD_CHARACTER);
        }

        @Nonnull
        @Override
        public Lexer getHighlightingLexer() {
            return new SvgPathLexer();
        }

        @Nonnull
        @Override
        public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
            return pack(ourAttributes.get(tokenType));
        }
    }
}
