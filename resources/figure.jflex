package com.cesar31.figures.lexerandparser;

import static com.cesar31.figures.lexerandparser.sym.*;
import java_cup.runtime.*;

%%

%class FigureLex
%type java_cup.runtime.Symbol
%public
%cup
%unicode
%line
%column

%{

	private Symbol symbol(int type) {
		return new Symbol(type, yyline + 1, yycolumn + 1);
	}

	private Symbol symbol(int type, Object value) {
		return new Symbol(type, yyline + 1, yycolumn + 1, value);
	}

%}

Number = [0-9]+
LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [\s\t\f]

%%

<YYINITIAL> {

	/* kerwords */
	"graficar"		{ return symbol(GRAPH, yytext()); }
	"circulo"		{ return symbol(CIRCLE, yytext()); }
	"cuadrado"		{ return symbol(SQUARE, yytext()); }
	"rectangulo"		{ return symbol(RCTNGL, yytext()); }
	"linea"			{ return symbol(LINE, yytext()); }
	"poligono"		{ return symbol(POLYGN, yytext()); }
	"animar"		{ return symbol(ANIMT, yytext()); }
	"objeto"		{ return symbol(OBJ, yytext()); }
	"anterior"		{ return symbol(ANT, yytext()); }
	"curva"			{ return symbol(CURVE, yytext()); }

	/* colors */
	"azul"			{ return symbol(COLOR, yytext()); }
	"rojo"			{ return symbol(COLOR, yytext()); }
	"verde"			{ return symbol(COLOR, yytext()); }
	"amarillo"		{ return symbol(COLOR, yytext()); }
	"naranja"		{ return symbol(COLOR, yytext()); }
	"morado"		{ return symbol(COLOR, yytext()); }
	"cafe"			{ return symbol(COLOR, yytext()); }
	"negro"			{ return symbol(COLOR, yytext()); }

	/* signs and operators */
	","			{ return symbol(COMMA, yytext()); }
	"("			{ return symbol(LPAREN, yytext()); }
	")"			{ return symbol(RPAREN, yytext()); }
	"+"			{ return symbol(PLUS, yytext()); }
	"-"			{ return symbol(MINUS, yytext()); }
	"*"			{ return symbol(TIMES, yytext()); }
	"/"			{ return symbol(DIV, yytext()); }

	/* Numbers */
	{Number}		{ return new Symbol(ENTERO, yyline + 1, yycolumn + 1, Integer.valueOf(yytext())); }

	/* whitespace */
	{WhiteSpace}		{ /* Ignore */ }

}

/* error */

[^]				{ return symbol(ERROR, yytext()); }
