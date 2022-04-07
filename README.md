# compilador

## Implemente um analisado léxico!
Seja:
• digito -> [0-9]
• letra -> [a-zA-Z]
Implemente o processo de varredura para os seguintes tokens:
• inteiro -> dígito+
• float -> dígito+”.”dígito+
• char -> 'letra' | 'dígito'
• identificador -> (letra | "_")(letra | "_" | dígito)*
• operador relacional -> < | > | <= | >= | == | !=
• operador aritmético -> + | - | * | /
• operador atribuição -> =
• caracter especial -> ) | ( | { | } | , | ;
• palavra reservada -> main | if | else | while | do | for | int | float | char
• Adicionar mais um elemento léxico por integrante do grupo.
Programa deverá:
• Ler arquivo texto com código na linguagem especificada acima
• Implementar método que retorne próximo token do texto, indicando seu tipo
(“getNextToken(): Token”).
o Classe Token deve armazenar minimamente o conteúdo e o tipo.
• Implementar método que imprima todos os tokens encontrados e respectivos tipos.
• O programa deverá imprimir mensagens de erro adequada ao problema encontrado:

o Mensagem deve ser específica: que tipo de erro? (ex. nome de variável
inválida, operado lógico inválido, etc.)
o Indicar último token lido.
o Tentar indicar: (1) linha do erro, (2) coluna do erro, etc.
• Dica: ignore os espaços, não tente utilizá-los como parte da lógica para saber quando
um token terminou e ou quando iniciou outro.

Avaliação:
o Apresentar ao professor código e máquinas de estado utilizadas
o Critérios:
o Assertividade em testes trazidos pelo professor
o Qualidade das mensagens de erro
o Segurança ao explicar o código: todos dos grupos serão perguntados sobre a
implementação.
o Máquinas de estado
o Pontuação máxima: 200