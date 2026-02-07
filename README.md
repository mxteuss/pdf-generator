# Como funciona? 

Sistema web completo para criação automática de elementos pré-textuais de trabalhos acadêmicos (TCC, monografias, dissertações) seguindo rigorosamente as normas ABNT NBR 14724:2011.

# 🚀 Tecnologias Utilizadas

<b><i>Backend</b></i>

Java - Linguagem de programação   
Spring Boot - Framework web  
OpenPDF/LibrePDF - Biblioteca para geração de PDFs  
Maven - Gerenciamento de dependências  

<b><i>Frontend</i></b>

HTML5 - Estrutura  
CSS3 - Estilização  
JavaScript (ES6+) - Interatividade  
Fetch API - Comunicação com backend  


# 🔧 Instalação
````
1. Clone o repositório
git clone https://github.com/mxteuss/FormatABNT.git
cd format-abnt

# 2. Instale as dependências
mvn clean install

# 3. Execute o backend
mvn spring-boot:run
````

# 📐 Especificações ABNT (NBR 14724:2011) 
O sistema gera documentos com formatação rigorosa seguindo a norma brasileira:  

Margens:  

Superior: 3cm  
Esquerda: 3cm  
Direita: 3cm  
Inferior: 2cm  

Formatação:  

Fonte: Times New Roman, tamanho 12  
Alinhamento: Centralizado (elementos pré-textuais)  
Espaçamento: Simples  
Título: Negrito e Maíusculo  

# 
Se este projeto te ajudou, dê uma estrela :)
