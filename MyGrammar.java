import computation.contextfreegrammar.*;
import jdk.jfr.Label;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MyGrammar {
	public static ContextFreeGrammar makeGrammar() {
/* 
    // test input 0011
		// Testing simple CNF CFG
		Variable A0 = new Variable("A0");
		Variable A = new Variable('A');
		Variable B = new Variable('B');
		Variable Z = new Variable('Z');
		Variable Y = new Variable('Y');

		HashSet<Variable> variables = new HashSet<>();
		variables.add(A0);
		variables.add(A);
		variables.add(B);
		variables.add(Z);
		variables.add(Y);

		Terminal zero = new Terminal('0');
		Terminal one = new Terminal('1');

		HashSet<Terminal> terminals = new HashSet<>();
		terminals.add(zero);
		terminals.add(one);

		ArrayList<Rule> rules = new ArrayList<>();
		rules.add(new Rule(A0, Word.emptyWord));
		rules.add(new Rule(A0, new Word(Z, Y)));
		rules.add(new Rule(A0, new Word(Z, B)));
		rules.add(new Rule(A, new Word(Z, Y)));
		rules.add(new Rule(A, new Word(Z, B)));
		rules.add(new Rule(B, new Word(A, Y)));
		rules.add(new Rule(Z, new Word(zero)));
		rules.add(new Rule(Y, new Word(one)));


		ContextFreeGrammar cfg = new ContextFreeGrammar(variables, terminals, rules, A0);
		return cfg;
*/
/*
    // test cfg from https://www.javatpoint.com/automata-chomskys-normal-form
    // test input 'aaaa'
		Variable S0 = new Variable("S0");
		Variable S = new Variable('S');
		Variable A = new Variable('A');
		Variable B = new Variable('B');
		Variable X = new Variable('X');
    Variable R = new Variable('R');
		HashSet<Variable> variables = new HashSet<>();
		variables.add(S0);
		variables.add(S);
		variables.add(A);
		variables.add(B);
		variables.add(X);
    variables.add(R);

		Terminal a = new Terminal('a');
		Terminal b = new Terminal('b');

		HashSet<Terminal> terminals = new HashSet<>();
		terminals.add(a);
		terminals.add(b);

		ArrayList<Rule> rules = new ArrayList<>();
		rules.add(new Rule(S0, new Word(a)));
		rules.add(new Rule(S0, new Word(b)));
		rules.add(new Rule(S0, new Word(X, A)));
		rules.add(new Rule(S0, new Word(A, X)));

    rules.add(new Rule(S, new Word(a)));
		rules.add(new Rule(S, new Word(X, A)));
		rules.add(new Rule(S, new Word(A, X)));
		rules.add(new Rule(S, new Word(b)));

		rules.add(new Rule(A, new Word(R, B)));

		rules.add(new Rule(B, new Word(A, X)));
		rules.add(new Rule(B, new Word(b)));
		rules.add(new Rule(B, new Word(a)));

		rules.add(new Rule(X, new Word(a)));

		rules.add(new Rule(R, new Word(X, B)));




		ContextFreeGrammar cfg = new ContextFreeGrammar(variables, terminals, rules, S0);
		return cfg;

*/


		Variable X = new Variable("X");
		Variable S = new Variable("S");
		Variable T = new Variable("T");
		Variable F = new Variable("F");
		Variable X1 = new Variable("X1");
		Variable X2 = new Variable("X2");
		Variable X3 = new Variable("X3");
		Variable P = new Variable("P");
		Variable M = new Variable("M");
		Variable L = new Variable("L");
		Variable R = new Variable("R");

		HashSet<Variable> variables = new HashSet<>();
		variables.add(X);
		variables.add(S);
		variables.add(T);
		variables.add(F);
		variables.add(X1);
		variables.add(X2);
		variables.add(X3);
		variables.add(P);
		variables.add(M);
		variables.add(L);
		variables.add(R);

		Terminal zero = new Terminal('0');
		Terminal one = new Terminal('1');
		Terminal plus = new Terminal('+');
		Terminal mult = new Terminal('*');
		Terminal x = new Terminal('x');
		Terminal Lbracket = new Terminal('(');
		Terminal Rbracket = new Terminal(')');

		HashSet<Terminal> terminals = new HashSet<>();
		terminals.add(zero);
		terminals.add(one);
		terminals.add(plus);
		terminals.add(mult);
		terminals.add(x);
		terminals.add(Lbracket);
		terminals.add(Rbracket);

		ArrayList<Rule> rules = new ArrayList<>();
		rules.add(new Rule(X, new Word(S, X1)));
		rules.add(new Rule(X, new Word(T, X2)));
		rules.add(new Rule(X, new Word(L, X3)));
		rules.add(new Rule(X, new Word(zero)));
		rules.add(new Rule(X, new Word(one)));
		rules.add(new Rule(X, new Word(x)));

		rules.add(new Rule(S, new Word(S, X1)));
		rules.add(new Rule(S, new Word(T, X2)));
		rules.add(new Rule(S, new Word(L, X3)));
		rules.add(new Rule(S, new Word(zero)));
		rules.add(new Rule(S, new Word(one)));
		rules.add(new Rule(S, new Word(x)));

		rules.add(new Rule(T, new Word(T, X2)));
		rules.add(new Rule(T, new Word(L, X3)));
		rules.add(new Rule(T, new Word(zero)));
		rules.add(new Rule(T, new Word(one)));
		rules.add(new Rule(T, new Word(x)));

		rules.add(new Rule(F, new Word(L, X3)));
		rules.add(new Rule(F, new Word(zero)));
		rules.add(new Rule(F, new Word(one)));
		rules.add(new Rule(F, new Word(x)));

		rules.add(new Rule(X1, new Word(P, T)));
		rules.add(new Rule(X2, new Word(M, F)));
		rules.add(new Rule(X3, new Word(S, R)));

		rules.add(new Rule(P, new Word(plus)));
		rules.add(new Rule(M, new Word(mult)));
		rules.add(new Rule(L, new Word(Lbracket)));
		rules.add(new Rule(R, new Word(Rbracket)));

		ContextFreeGrammar cfg = new ContextFreeGrammar(variables, terminals, rules, X);
		return cfg;
    
	}
}
