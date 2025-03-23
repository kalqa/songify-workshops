package com.songify.domain.crud;

class SongifyCrudFacadeConfiguration {

    public static SongifyCrudFacade createSongifyCrud(final SongRepository songRepository,
                                                      final GenreRepository genreRepository) {
        SongRetriever songRetriever = new SongRetriever(songRepository);
        SongUpdater songUpdater = new SongUpdater(songRepository);
        GenreDeleter genreDeleter = new GenreDeleter(genreRepository);
        SongDeleter songDeleter = new SongDeleter(songRepository, songRetriever, genreDeleter);
        GenreRetriever genreRetriever = new GenreRetriever(genreRepository);
        GenreAssigner genreAssigner = new GenreAssigner(songRetriever, genreRetriever);
        SongAdder songAdder = new SongAdder(songRepository, genreAssigner);
        GenreAdder genreAdder = new GenreAdder(genreRepository);
        return new SongifyCrudFacade(
                songRetriever,
                songUpdater,
                songDeleter,
                songAdder,
                genreAdder,
                genreRetriever,
                genreAssigner
        );
    }
}
