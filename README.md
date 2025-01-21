# Chess Wizard

Chess Wizard is a wizard who plays chess (using Clojure!).

Accompanied by a chess rule enforcer and a graphical user interface.

See if you can beat the wizard...

## Live Demo

https://chesswizard.cyberdesignfactory.com

## Installation

Ensure the following are installed:
- Java
- Leiningen
- node/npm/npx

Steps:
- git clone https://github.com/cyberdesignfactory/chesswizard
- cd domain/chess
- lein test  # optional
- lein install
- cd ../wizard
- lein test  # optional
- lein install
- cd ../../apps/viewer
- npm i
- npx shadow-cljs watch app

## TODO

- Fully enforce castling rules
- Wizard should value developing its pieces, not just material
- Wizard should avoid checkmate and value checkmating
- Notation supports disambiguation

## License

Copyright © 2025 Robert J Symes

Distributed under the Eclipse Public License, the same as Clojure.


