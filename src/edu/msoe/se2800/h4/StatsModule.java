
package edu.msoe.se2800.h4;

import dagger.Module;
import dagger.Provides;

@Module(complete = false)
public class StatsModule {

    @Provides
    StatsTimerDaemon provideStatsTimerDaemon(StatsTimerDaemon timer) {
        return timer;
    }

}
