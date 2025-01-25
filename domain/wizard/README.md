# Wizard

The Wizard decides which move to make, given a board position.

There is a function which returns a 'score' for any given board position (currently based just on total material minus opponent's total material).

It then uses the Minimax algorithm to find what it considers to be the best move.

## Usage

lein test  # optional
lein install

## License

Copyright © 2025 Robert J Symes

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
