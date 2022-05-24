// Generated from D:/Java/workspaces/stellaris-config-parser\Stellaris.g4 by ANTLR 4.9.1
package net.turanar.stellaris.antlr;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class StellarisLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, BOOLEAN=3, VARIABLE=4, SPECIFIER=5, NUMBER=6, DATE=7, 
		BAREWORD=8, STRING=9, WS=10, LINE_COMMENT=11;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "BOOLEAN", "VARIABLE", "SPECIFIER", "NUMBER", "DATE", 
			"BAREWORD", "STRING", "WS", "LINE_COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "BOOLEAN", "VARIABLE", "SPECIFIER", "NUMBER", "DATE", 
			"BAREWORD", "STRING", "WS", "LINE_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public StellarisLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Stellaris.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\r\u0099\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\5\4,\n\4\3\5\3\5\3\5\7\5\61\n\5\f\5\16\5\64\13\5\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6@\n\6\3\7\5\7C\n\7\3\7\6\7F\n"+
		"\7\r\7\16\7G\3\7\3\7\5\7L\n\7\3\7\6\7O\n\7\r\7\16\7P\3\7\3\7\6\7U\n\7"+
		"\r\7\16\7V\3\7\5\7Z\n\7\3\7\6\7]\n\7\r\7\16\7^\5\7a\n\7\3\b\6\bd\n\b\r"+
		"\b\16\be\3\b\3\b\6\bj\n\b\r\b\16\bk\3\b\3\b\6\bp\n\b\r\b\16\bq\3\t\5\t"+
		"u\n\t\3\t\3\t\7\ty\n\t\f\t\16\t|\13\t\3\t\5\t\177\n\t\3\n\3\n\7\n\u0083"+
		"\n\n\f\n\16\n\u0086\13\n\3\n\3\n\3\13\6\13\u008b\n\13\r\13\16\13\u008c"+
		"\3\13\3\13\3\f\3\f\7\f\u0093\n\f\f\f\16\f\u0096\13\f\3\f\3\f\2\2\r\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\3\2\13\4\2C\\c|\b\2\'"+
		"\'/\60\62;C\\aac|\4\2>>@@\3\2\62;\t\2##&&\62;C\\^^c|~~\7\2##&^aac|~~\3"+
		"\2$$\5\2\13\f\17\17\"\"\4\2\f\f\17\17\2\u00b3\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\3\31\3\2\2\2\5\33\3\2\2"+
		"\2\7+\3\2\2\2\t-\3\2\2\2\13?\3\2\2\2\r`\3\2\2\2\17c\3\2\2\2\21t\3\2\2"+
		"\2\23\u0080\3\2\2\2\25\u008a\3\2\2\2\27\u0090\3\2\2\2\31\32\7}\2\2\32"+
		"\4\3\2\2\2\33\34\7\177\2\2\34\6\3\2\2\2\35\36\7{\2\2\36\37\7g\2\2\37,"+
		"\7u\2\2 !\7p\2\2!,\7q\2\2\"#\7v\2\2#$\7t\2\2$%\7w\2\2%,\7g\2\2&\'\7h\2"+
		"\2\'(\7c\2\2()\7n\2\2)*\7u\2\2*,\7g\2\2+\35\3\2\2\2+ \3\2\2\2+\"\3\2\2"+
		"\2+&\3\2\2\2,\b\3\2\2\2-.\7B\2\2.\62\t\2\2\2/\61\t\3\2\2\60/\3\2\2\2\61"+
		"\64\3\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2\63\n\3\2\2\2\64\62\3\2\2\2\65"+
		"@\7?\2\2\66\67\7>\2\2\67@\7@\2\28@\t\4\2\29:\7>\2\2:@\7?\2\2;<\7@\2\2"+
		"<@\7?\2\2=>\7#\2\2>@\7?\2\2?\65\3\2\2\2?\66\3\2\2\2?8\3\2\2\2?9\3\2\2"+
		"\2?;\3\2\2\2?=\3\2\2\2@\f\3\2\2\2AC\7/\2\2BA\3\2\2\2BC\3\2\2\2CE\3\2\2"+
		"\2DF\t\5\2\2ED\3\2\2\2FG\3\2\2\2GE\3\2\2\2GH\3\2\2\2HI\3\2\2\2Ia\7\'\2"+
		"\2JL\7/\2\2KJ\3\2\2\2KL\3\2\2\2LN\3\2\2\2MO\t\5\2\2NM\3\2\2\2OP\3\2\2"+
		"\2PN\3\2\2\2PQ\3\2\2\2QR\3\2\2\2RT\7\60\2\2SU\t\5\2\2TS\3\2\2\2UV\3\2"+
		"\2\2VT\3\2\2\2VW\3\2\2\2Wa\3\2\2\2XZ\7/\2\2YX\3\2\2\2YZ\3\2\2\2Z\\\3\2"+
		"\2\2[]\t\5\2\2\\[\3\2\2\2]^\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_a\3\2\2\2`B\3"+
		"\2\2\2`K\3\2\2\2`Y\3\2\2\2a\16\3\2\2\2bd\t\5\2\2cb\3\2\2\2de\3\2\2\2e"+
		"c\3\2\2\2ef\3\2\2\2fg\3\2\2\2gi\7\60\2\2hj\t\5\2\2ih\3\2\2\2jk\3\2\2\2"+
		"ki\3\2\2\2kl\3\2\2\2lm\3\2\2\2mo\7\60\2\2np\t\5\2\2on\3\2\2\2pq\3\2\2"+
		"\2qo\3\2\2\2qr\3\2\2\2r\20\3\2\2\2su\7$\2\2ts\3\2\2\2tu\3\2\2\2uv\3\2"+
		"\2\2vz\t\6\2\2wy\t\7\2\2xw\3\2\2\2y|\3\2\2\2zx\3\2\2\2z{\3\2\2\2{~\3\2"+
		"\2\2|z\3\2\2\2}\177\7$\2\2~}\3\2\2\2~\177\3\2\2\2\177\22\3\2\2\2\u0080"+
		"\u0084\7$\2\2\u0081\u0083\n\b\2\2\u0082\u0081\3\2\2\2\u0083\u0086\3\2"+
		"\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0087\3\2\2\2\u0086"+
		"\u0084\3\2\2\2\u0087\u0088\7$\2\2\u0088\24\3\2\2\2\u0089\u008b\t\t\2\2"+
		"\u008a\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008d"+
		"\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008f\b\13\2\2\u008f\26\3\2\2\2\u0090"+
		"\u0094\7%\2\2\u0091\u0093\n\n\2\2\u0092\u0091\3\2\2\2\u0093\u0096\3\2"+
		"\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0097\3\2\2\2\u0096"+
		"\u0094\3\2\2\2\u0097\u0098\b\f\3\2\u0098\30\3\2\2\2\27\2+\62?BGKPVY^`"+
		"ekqtz~\u0084\u008c\u0094\4\b\2\2\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}