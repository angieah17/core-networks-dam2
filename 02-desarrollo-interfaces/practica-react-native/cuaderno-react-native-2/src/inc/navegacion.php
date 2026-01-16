<section class="desarrollo">
  <h3>1. ¿Qué es un “navegador”?</h3>
    <p>
      Podemos imaginarlo como un director de orquesta que sabe qué pantalla mostrar en cada momento.</p>
    <p> Nosotros solo le decimos:</p>
    <ul>
      <li>“Muéstrame la Página 1”</li>
      <li>“Ahora quiero ir a la Página 2”</li>
    </ul>

  <p> Y él se encarga de cambiar la vista.</p>

  <h3>2. ¿Qué es una pantalla?</h3>
    <p>Una pantalla es simplemente un componente que representa una vista completa de la app.</p>
    <p>Por ejemplo:</p>

    <ul>
      <li>Pantalla 1 </li>
      <li>Pantalla 2 </li>
      <li>Pantalla 3 </li>
    </ul>
    <p>Cada pantalla es independiente y podemos colocar dentro lo que queramos (texto, botones, imágenes…).</p>

    <h3>3. ¿Cómo se conectan las pantallas entre sí?</h3>
      <p>Las pantallas se registran en un navegador de tipo <strong>Stack</strong>.</p>
      <p>Pensad en él como una lista organizada de pantallas.</p>
      <p>Una vez que las pantallas están registradas, podemos movernos entre ellas usando un método de navegación.</p>
      <p>Básicamente le decimos al navegador:</p>
      <p><em>“Llévanos a la pantalla que tiene este nombre.”</em></p>

    <h3>4. ¿Dónde ponemos los enlaces de navegación?</h3>
      <p>Podemos colocar los enlaces donde nos dé la gana:</p>
      <ul>
        <li>En un menú principal</li>
        <li>En botones</li>
        <li>En tarjetas</li>
        <li>En iconos</li>
        <li>En una barra inferior</li>
      </ul>
      <p>Lo importante es que cada enlace llama al sistema de navegación y le pide abrir una pantalla concreta.</p>

    <h3>5. ¿Qué necesitamos para tener tres páginas y tres enlaces?</h3>
      <p>Muy sencillo, necesitamos:</p>
      <ul>
        <li>Tres pantallas creadas (cada una en su archivo).</li>
        <li>Un navegador (Stack) que las registre.</li>
        <li>Tres enlaces (botones o lo que queráis usar) que pidan ir a cada pantalla.</li>
      </ul>
      <p>Nada más. El resto es decoración.</p>

    <h3>6. ¿Qué ocurre cuando cambiamos de pantalla?</h3>
      <p>El navegador:</p>
      <ul>
        <li>Oculta la pantalla actual.</li>
        <li>Muestra la nueva.</li>
        <li>Mantiene un “historial” para poder volver atrás si queremos.</li>
      </ul>
      <p>Es como una pila de cartas: la última que ponemos arriba es la que vemos.</p>

    <h3>7. ¿Por qué usar un Stack y no otra cosa?</h3>
      <p>Porque:</p>
      <ul>
        <li>Es fácil de entender.</li>
        <li>Es el tipo de navegación más común.</li>
        <li>Se comporta como las aplicaciones nativas de toda la vida.</li>
      </ul>

</section>