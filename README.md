</p><h1 class="code-line" data-line-start=0 data-line-end=1 ><a id="Readme_API_Desafio_Desenvolvedor_BackEnd_0"></a>Readme: API- Desafio Desenvolvedor Back-End</h1>
<p class="has-line-data" data-line-start="1" data-line-end="2">Essa API foi construida para atender os requisitos no teste disponibilizado.</p>
<h2 class="code-line" data-line-start=3 data-line-end=4 ><a id="Requisitos_3"></a>Requisitos</h2>
<ul>
<li class="has-line-data" data-line-start="4" data-line-end="5">Spring Framework</li>
<li class="has-line-data" data-line-start="5" data-line-end="7">SpringBoot</li>
</ul>
<h2 class="code-line" data-line-start=7 data-line-end=8 ><a id="Dependencies_7"></a>Dependencies</h2>
<p class="has-line-data" data-line-start="8" data-line-end="9">Há várias dependências de terceiros usadas no projeto. Navegue no arquivo pom.xml do Maven para obter detalhes das bibliotecas e versões usadas.</p>
<h2 class="code-line" data-line-start=10 data-line-end=11 ><a id="Building_the_project_10"></a>Building the project</h2>
<p class="has-line-data" data-line-start="11" data-line-end="12">Você vai precisar:</p>
<ul>
<li class="has-line-data" data-line-start="13" data-line-end="14">Java JDK 17</li>
<li class="has-line-data" data-line-start="14" data-line-end="15">Maven 3.6.3 ou superior</li>
<li class="has-line-data" data-line-start="15" data-line-end="17">Git</li>
<li class="has-line-data" data-line-start="15" data-line-end="17">PostgreSQL 12 </li>
</ul>

<p class="has-line-data" data-line-start="17" data-line-end="18">Para se conectar com o banco de dados, use as seguintes configurações no arquivo [application.properties] do projeto.</p>

<p class="has-line-data" data-line-start="17" data-line-end="18">Primeiro crie uma banco de dados com o nome "darm-api", e declare no arquivo, como podemos ver abaixo. Depois digite o usuario do DB e sua senha.</p>
<code>spring.datasource.url=jdbc:postgresql://localhost:5433/darm-api
spring.datasource.username=usuario<br>
spring.datasource.password=senha</code>

<p class="has-line-data" data-line-start="17" data-line-end="18">O restante das configurações podém permanecer do modo que estão.</p>

<code>spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true </code>
<br><code>spring.jpa.hibernate.ddl-auto=update </code>
<br><code>spring.jpa.show-sql=true</code>
<br><code>spring.jpa.properties.hibernate.format_sql=true </code>
<br><code>spring.jpa.properties.hibernate.show_sql=true </code>
<br><code>spring.jpa.properties.hibernate.use_sql_comments=true </code>

<p class="has-line-data" data-line-start="17" data-line-end="18">Execute o comando mvn clean install para baixar as dependências do projeto através do maven.</p>
<pre><code>$ mvn clean install
</code></pre>
<p class="has-line-data" data-line-start="21" data-line-end="22">Execute o comando mvn spring-boot:run para executar a sua aplicação. Você também pode utilizar o mvnw.</p>
<pre><code>$ mvn spring-boot:run
</code></pre>
<p class="has-line-data" data-line-start="25" data-line-end="26">Se você preferir, também pode-se fazer o build da aplicação por sua IDE.</p>
<p class="has-line-data" data-line-start="27" data-line-end="28">Você pode testar os endpoints da API com o Cliente do Postman.</p>
<p class="has-line-data" data-line-start="29" data-line-end="31">POST - Cadastrar Cliente<br>
<a href="http://localhost:8080/api/v1/cadastrar-usuario">http://localhost:8080/api/v1/cadastrar-usuario</a></p>
<p class="has-line-data" data-line-start="32" data-line-end="42"><code>{ &quot;nome&quot;: &quot;Maria Fontes&quot;, &quot;cpf&quot;: &quot;78256841778&quot;, &quot;email&quot;: &quot;mariaFontes34@gmail.com&quot;, &quot;telefone&quot;: &quot;21958963254&quot;, &quot;endereco&quot;: &quot;Rua da Esperança&quot;, &quot;tipoDaConta&quot;: &quot;Corrente&quot;, &quot;senha&quot;: &quot;Passwd&quot;, &quot;confirSenha&quot;: &quot;Passwd&quot; }</code></p>
<p class="has-line-data" data-line-start="43" data-line-end="45">POST - Login-Cliente<br>
<a href="http://localhost:8080/api/v1/login">http://localhost:8080/api/v1/login</a></p>
<p class="has-line-data" data-line-start="46" data-line-end="52"><code>{ &quot;cpf&quot;: &quot;78256841776&quot;, &quot;senha&quot;: &quot;Passwd&quot; }</code><br>
POST - Login-Adm<br>
<a href="http://localhost:8080/api/v1/adm/login">http://localhost:8080/api/v1/adm/login</a></p>
<p class="has-line-data" data-line-start="53" data-line-end="57"><code>{ &quot;cpf&quot;: &quot;78256841776&quot;, &quot;senha&quot;: &quot;Passwd&quot; }</code></p>
<p class="has-line-data" data-line-start="58" data-line-end="60">POST - Recuperar Senha-Cliente<br>
<a href="http://localhost:8080/api/v1/recuperar-senha">http://localhost:8080/api/v1/recuperar-senha</a></p>
<p class="has-line-data" data-line-start="61" data-line-end="66"><code>{ &quot;email&quot;: &quot;mariaFontes34@gmail.com&quot;, &quot;nome&quot;: &quot;Maria Fontes&quot;, &quot;cpf&quot;: &quot;78256841778&quot; }</code></p>
<p class="has-line-data" data-line-start="67" data-line-end="69">POST - Criar-Cartão<br>
<a href="http://localhost:8080/api/v1/criar-cartao">http://localhost:8080/api/v1/criar-cartao</a></p>
<p class="has-line-data" data-line-start="70" data-line-end="78"><code>{ &quot;numero&quot;: &quot;4856 9854 3254 7456&quot;, &quot;bandeira&quot;: &quot;Master Card&quot;, &quot;cvc&quot;: &quot;458&quot;, &quot;tipoDeCartao&quot;: &quot;Credito&quot;, &quot;anosDeValidade&quot;: &quot;4&quot;, &quot;nomeCliente&quot;: &quot;Maria Fontes&quot; }</code></p>
<p class="has-line-data" data-line-start="79" data-line-end="81">POST - Adicionar Foto<br>
<a href="http://localhost:8080/api/v1/editar-perfil/adicionar-foto">http://localhost:8080/api/v1/editar-perfil/adicionar-foto</a></p>
<p class="has-line-data" data-line-start="82" data-line-end="87"><code>{ &quot;urlFoto&quot;: &quot;https://www.google.com/url?sa=i&amp;url=https%3A%2F%2Fwww.flaticon.com&quot;, &quot;cpf&quot;: &quot;78256841778&quot;, &quot;senha&quot;: &quot;Passwd&quot; }</code></p>
<p class="has-line-data" data-line-start="88" data-line-end="90">POST - Mudar Senha-Cliente<br>
<a href="http://localhost:8080/api/v1/editar-perfil/mudar-senha">http://localhost:8080/api/v1/editar-perfil/mudar-senha</a></p>
<p class="has-line-data" data-line-start="91" data-line-end="96"><code>{ &quot;cpf&quot;: &quot;78256841778&quot;, &quot;senha&quot;: &quot;Passwd&quot;, &quot;novaSenha&quot;: &quot;Senha&quot; }</code></p>
<p class="has-line-data" data-line-start="97" data-line-end="99">DEL - Deletar Perfil<br>
<a href="http://localhost:8080/api/v1/editar-perfil/deletar">http://localhost:8080/api/v1/editar-perfil/deletar</a></p>
<p class="has-line-data" data-line-start="100" data-line-end="105"><code>{ &quot;cpf&quot;: &quot;78256841778&quot;, &quot;senha&quot;: &quot;Passwd&quot;, &quot;email&quot;: &quot;mariaFontes34@gmail.com&quot; }</code></p>
<p class="has-line-data" data-line-start="106" data-line-end="108">POST - Cadastrar-Adm<br>
<a href="http://localhost:8080/api/v1/adm/cadastrar-adm">http://localhost:8080/api/v1/adm/cadastrar-adm</a></p>
<p class="has-line-data" data-line-start="109" data-line-end="117"><code>{ &quot;nome&quot;: &quot;Lucas Farias&quot;, &quot;cpf&quot;: &quot;85441236987&quot;, &quot;email&quot;: &quot;lucasfarias@gmail.com&quot;, &quot;endereco&quot;: &quot;Rua Antonio&quot;, &quot;estado&quot;: &quot;Ceará&quot;, &quot;senha&quot;: &quot;Passwd&quot; }</code></p>
<p class="has-line-data" data-line-start="118" data-line-end="120">POST - Recuperar-Senha-Adm<br>
<a href="http://localhost:8080/api/v1/adm/recuperar-senha">http://localhost:8080/api/v1/adm/recuperar-senha</a></p>
<p class="has-line-data" data-line-start="121" data-line-end="126"><code>{ &quot;email&quot;: &quot;lucasfarias@gmail.com&quot;, &quot;nome&quot;: &quot;Lucas Farias&quot;, &quot;cpf&quot;: &quot;85441236987&quot; }</code></p>
<p class="has-line-data" data-line-start="127" data-line-end="129">POST - Aprovar-Cartão-Adm<br>
<a href="http://localhost:8080/api/v1/adm/aprovar-cartao">http://localhost:8080/api/v1/adm/aprovar-cartao</a></p>
<p class="has-line-data" data-line-start="130" data-line-end="135"><code>{ &quot;num&quot;: &quot;4856 9854 3254 7456&quot;, &quot;cvc&quot;: &quot;458&quot;, &quot;status&quot;: 2 }</code></p>
<p class="has-line-data" data-line-start="136" data-line-end="141">GET - Listar Clientes por Estado<br>
<a href="http://localhost:8080/api/v1/adm/listar-porEstado?estado=Cear%C3%A1">http://localhost:8080/api/v1/adm/listar-porEstado?estado=Ceará</a><br>
Query Params<br>
estado<br>
Ceará</p>
<p class="has-line-data" data-line-start="142" data-line-end="147">GET - Listar Clientes por Quantidade de Cartões<br>
<a href="http://localhost:8080/api/v1/adm/listar-qtdCartoes?numero_de_cartoes=1">http://localhost:8080/api/v1/adm/listar-qtdCartoes?numero_de_cartoes=1</a><br>
Query Params<br>
numero_de_cartoes<br>
1</p>
<p class="has-line-data" data-line-start="148" data-line-end="150">GET - Listar Clientes por Ordem Alfabetica<br>
<a href="http://localhost:8080/api/v1/adm/listar-ordem-alfabetica">http://localhost:8080/api/v1/adm/listar-ordem-alfabetica</a></p>
