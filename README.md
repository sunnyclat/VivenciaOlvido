# VivenciaOlvido

Juego de aventura en donde a traves de decisiones el jugador se puede adentrar en un mundo en donde cada eleccion puede representar un avance o un fracaso.

Se presentan 3 opciones iniciales en donde el jugador debera decidir. Primero que nada, el juego dispone de un sistema de temporizacion en donde cada eleccion que el jugador tome debera ser en menos de 8 segundos, de no hacerse, el mismo juego usa un sistema random para poder decidir arbitrariamente lo que podria conllevar a una mala o buena decision. De ahi existen 3 opciones de juego distintas, una en donde el jugador deba escribir la palabra en mayuscula de la decision a tomar y eso en menos de 8 segundos, otra en donde tambien se conserva el temporizador pero se puede tomar la decision mediante numeros del 1 al 3 y la ultima en donde no existe ningun tipo de temporizador y se pueden tomar las decisiones mediante numeros como la opcion 2 (esta seria la forma mas sencilla y comoda de progresar en la travesia.

Cada escenario puede restarte vida o llenarte vida mediante un medikit que encuentre el personaje. 

El personaje dispone de un inventario en donde puede ir almacenando los items que vaya encontrando, los cuales pueden ayudarle en su mision para poder seguir progresando en la aventura.

El proyecto dispone de un txt el cual el programa ira leyendo para ir tomando las decisiones que seleccione el jugador. Este txt se puede modificar a gusto mientras se respete lo siguiente: siempre sean 3 opciones las que tome el jugador, las cuales se representara con un <. Cada opcion vacia debe estar representada con un punto unicamente. Si un escenario no genera ni saca vida debe estar escrito 0. Si el comienzo de un escenario contiene uno o varios items que el personaje añadira al inventario, estos deberan estar escritos en minuscula entre comillas simples. Si entramos a un inventario en donde necesitaremos un item para poder progresar (por ejemplo una llave para atravesar una puerta), este item necesario debera estar escrito en mayuscula y al final del texto. Las elecciones que tome el jugador en cada opcion deberan estar escritras en mayuscula.

Añadi algunos sonidos de puertas sin abrir o puertas rompiendose los cuales estan en  la clase "sonidos".

