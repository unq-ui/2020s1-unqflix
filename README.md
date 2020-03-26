# UNQ » UIs » Dominio » UNQFlix

[![Build Status](https://travis-ci.org/unq-ui/unqflix.svg?branch=master)](https://travis-ci.org/unq-ui/unqflix)
[![JitPack](https://jitpack.io/v/unq-ui/unqflix.svg)](https://jitpack.io/#unq-ui/unqflix)
[![Coverage Status](https://coveralls.io/repos/github/unq-ui/unqflix/badge.svg)](https://coveralls.io/github/unq-ui/unqflix)
[![Maintainability](https://api.codeclimate.com/v1/badges/78895f141af36a348d36/maintainability)](https://codeclimate.com/github/unq-ui/unqflix/maintainability)

Construcción de Interfaces de Usuario, Universidad Nacional de Quilmes.

## TP 2020s1

### Dependencia

Agregar el repositorio:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Agregar la dependencia:

```xml
<dependency>
    <groupId>com.github.unq-ui</groupId>
    <artifactId>unqflix</artifactId>
    <version>1.2.0</version>
</dependency>
```

### Interfaz de uso

```kt
domain.UNQFlix
    searchMovies(text: String): List<Movie>
    searchSeries(text: String): List<Serie>

    @throw ExistException
    addUser(user: User): Boolean 

    @throw ExistException
    addCategory(category: Category): Boolean

    @throw ExistException
    addMovie(movie: Movie): Boolean

    @throw ExistException
    addSerie(serie: Serie): Boolean

    @throw NotFoundException
    addSeason(idSerie: String, season: Season): Boolean

    @throw NotFoundException
    addChapter(idSerie: String, idSeason: String, chapter: Chapter): Boolean

    addBanner(banner: Content): Boolean

    deleteBanner(banner: Content): Boolean
    deleteMovie(movieId: String): Boolean
    deleteSerie(serieId: String): Boolean

    @throw NotFoundException
    deleteSeason(idSerie: String, idSeason: String): Boolean

    @throw NotFoundException
    deleteChapter(idSerie: String, idSeason: String, idChapter: String): Boolean

    addLastSeen(idUser: String, idContent: String): Unit
    addOrDeleteFav(idUser: String, idContent: String): Unit
```
