# Pokédex web application

The application functions as an encyclopedia to the pokemon universe.

## Installation

Not implemented yet.

## Usage

Not implemented yet.

## About

The backend (spring boot) fetches the pokemon data from a 3rd party api (https://pokeapi.co/) and uses react to display the content(`https://github.com/vidomark/pokedex-frontend`).
On the main page there is a list of pokemons available.
We can navigate to a pokemon profile by clikcing on a card and the details are shown properly. Sorting is available by ability or by pokemon type.

## Structure

The application is based upon a layered architecture which implements the MVC design pattern. The endpoints are predefined in the controller(s),
which then communicates the requests to the service layer. The dao implementation then handles the business logic, then the response is sent back vica versa.
The react application then displays the content.
