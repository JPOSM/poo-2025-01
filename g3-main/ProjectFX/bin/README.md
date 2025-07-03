🗂️ Relatório Final do Projeto: Geração de Energia a partir de Lixo Seco e Molhado

🎯 **1. Objetivo Geral**

O projeto visa simular o reaproveitamento energético de materiais descartados pela sociedade, focando em duas categorias principais de lixo:

* 🔄 **Lixo Molhado**: convertido em metano para geração de energia via turbinas.
* 🔥 **Lixo Seco**: convertido diretamente por meio da queima de materiais (madeira, tecidos, papéis).

A proposta simula cenários reais de reaproveitamento e avalia as perdas e eficiências térmicas no processo.

---

🧱 **2. Estrutura do Projeto**

O projeto foi dividido em módulos principais, utilizando os princípios da programação orientada a objetos (POO):

### ♻️ Módulo Lixo Molhado

* Calcula a quantidade de metano gerado a partir da massa de lixo.
* Converte a massa em energia utilizando dados reais de entalpia.
* Simula o uso dessa energia em turbinas, estimando a potência gerada.
* Salva relatórios automáticos em HTML e arquivos serializados.

### 🔥 Módulo Lixo Seco

* Recebe massas de madeira, tecidos e papéis.
* Calcula a energia aparente, útil e perdida com base em eficiência fornecida pelo usuário.
* Exibe relatórios no terminal com dados convertidos para Joules, Wh e kWh.

### 🔗 Integração Geral

* Os módulos foram integrados em um projeto coeso.
* Foi criado um ponto de entrada com interface terminal para que o usuário simule ambos os sistemas.

---

👥 **3. Tabela de Participantes**

| Participante   | 💮 Responsabilidades             |
| -------------- | -------------------------------- |
| Pablo Henrique | Lixo Seco; Integração de códigos |
| João Moynier   | Lixo Molhado; Banco de dados     |
| Ítalo Píres e Arthur Jardim | Interface e apresentação visual  |

---

🧰 **4. Tecnologias e Ferramentas Utilizadas**

| Plataforma/Ferramenta | 💼 Uso no Projeto                        |
| --------------------- | ---------------------------------------- |
| Java                  | Linguagem principal                      |
| BlueJ                 | Ambiente de desenvolvimento              |
| GitHub                | Versionamento de código                  |
| SQL (conceitual)      | Planejamento de banco de dados futuro    |
| Mermaid.js            | Diagramas de classes                     |
| HTML                  | Geração automática de relatórios visuais |

---

📘 **5. Conceitos Acadêmicos Envolvidos**

* ⌐ Conversão de unidades de energia (J, Wh, kWh)
* 🔥 Eficiência térmica e perdas por calor
* ⚖️ Massa molar, densidade, entalpia
* 📂 Serialização de objetos e arquivos
* 🌐 Geração dinâmica de arquivos HTML
* 🧩 Diagramas UML (com Mermaid.js)

---

🖥️ **6. Execução do Projeto**

* ✅ Compile o projeto completo em sua IDE Java (recomendado: BlueJ).

* ▶️ Execute a classe `Main` no pacote `app`.

* 🧲 Forneça os valores solicitados:

  * Massa de lixo molhado (toneladas)
  * Massa de materiais secos (kg)
  * Eficiência da queima (valor entre 0 e 1)
  * Área da turbina (m²)

* 📊 Os resultados serão exibidos no terminal e salvos automaticamente.

---

📟 **7. Códigos-Fonte e Diagramas UML (Mermaid.js)**

* [Diagrama de Casos de Uso](Diagramas/USO.md)
* [Diagrama de Classes](Diagramas/CLASSES.md)
* [Diagrama de Sequência](Diagramas/SEQUENCIA.md)

> O GitHub irá renderizá-los automaticamente quando abertos.

---

📌 **8. Considerações Finais**

Este projeto buscou aliar conceitos de física, sustentabilidade e programação orientada a objetos, fornecendo uma simulação educacional do potencial de reaproveitamento de lixo para geração de energia.

A estrutura modular e bem documentada permite expansões futuras com interface gráfica, banco de dados real e até aplicações web.
