package com.songify.domain.crud;

class SongifyCrudFacadeConfiguration {

    public static SongifyCrudFacade createSongifyCrud(final SongRepository songRepository) {
        SongRetriever songRetriever = new SongRetriever(songRepository);
        SongUpdater songUpdater = new SongUpdater(songRepository);
        SongDeleter songDeleter = new SongDeleter(songRepository, songRetriever);
        SongAdder songAdder = new SongAdder(songRepository);
        return new SongifyCrudFacade(
                songRetriever,
                songUpdater,
                songDeleter,
                songAdder
        );
    }
}
