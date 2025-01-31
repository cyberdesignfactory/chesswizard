
# Chess Wizard

Chess Wizard is a wizard who plays chess using Clojure!

Accompanied by a chess rule enforcer and a graphical user interface.

See if you can beat The Wizard...

<img src="https://www.cyberdesignfactory.com/img/chesswizard.png" xxalign="right" width="360" />

## Live Demo

https://chesswizard.cyberdesignfactory.com

## Installation / Running Locally

Ensure the following are installed:
- Java
- Leiningen
- node/npm/npx

Steps:
- git clone https://github.com/cyberdesignfactory/chesswizard
- cd chesswizard
- cd domain/chess
- lein test  # optional
- lein install
- cd ../wizard
- lein test  # optional
- lein install
- cd ../../apps/viewer
- npm i
- npx shadow-cljs watch app

The webapp should now be running at http://localhost:8280

## TODO

- Wizard should avoid checkmate and value checkmating
- Have board shrink if necessary for smaller width screens
- Port to re-frame
- Improve namespaces (cdf.cw.*)
- Display reason why a move is invalid (when user attempts to select 'to' square)
- Notation supports disambiguation
- Wizard could perhaps think more moves ahead when less pieces on the board

## Rationale

TODO

## License

Copyright © 2025 Robert J Symes

Distributed under the Eclipse Public License, the same as Clojure.


