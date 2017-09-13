# parsnips
> parse and analyze text snippets using CoreNLP

An interactive NLP sandbox.

Parnips is a web application built upon Stanford's state-of-the-art NLP toolkit <a href="https://stanfordnlp.github.io/CoreNLP/index.html">CoreNLP</a>. 

Stanford CoreNLP's site describes CoreNLP as:

`A set of human language technology tools. It can give the base forms of words, their parts of speech, whether they are   names          of companies, people, etc., normalize dates, times, and numeric quantities, mark up the structure of sentences in terms of phrases and syntactic dependencies, indicate which noun phrases refer to the same entities, indicate sentiment, extract particular or open-class relations between entity mentions, get the quotes people said, etc.`


The backend of parsnips is built in Clojure, which offers an concise and expressive syntax for working with CoreNLP tools; Ring and Compojure are used to create the RESTful web service. On the frontend, Vue.js is used to build out the user interface, while Chart.js and vue-chart.js are utilized for creating various charts and graphs, such as the word frequency distribution chart.

The target language is currently English; however, there are plans to incorporate as many languages as CoreNLP supports (for instance, Arabic, Chinese, French, German, and Spanish).


### Supported natural language processing features:
<ul>
<li>Tokenization</li>
<li>Word Frequency Distribution</li>
<li>Named-Entity Recognition</li>
<li>Lemmatization</li>
<li>Part of Speech Tagging</li>
<li>Constituency Parsing</li>
</ul>

#### Usage
Clone repo

`git clone https://github.com/p-adams/parsnips`

##### Start server:

`cd parsnips/server`

With lein installed, run `lein ring server`

Starts server on port 3000

##### Start client dev server:

Install dependencies: `cd parsnips/client && npm install`

Start server: `npm run dev`

### Todo

<ul>
  <li>Create tree hierarchy interface for parser feature</li>
</ul>




