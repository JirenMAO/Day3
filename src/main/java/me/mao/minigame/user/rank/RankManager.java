package me.mao.minigame.user.rank;

import me.mao.minigame.Core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RankManager {

    private Set<Rank> ranks;
    private RankData data;
    private Core core = Core.getInstance();

    public void loadRanks() {
        data = new RankData(core, "ranks");
        ranks = new HashSet<>();

        data.getKeys(false).forEach(String -> loadRank(String));
        core.getLogger().info("LOADED ALL RANKS!");
    }

    public Rank loadRank(String name) {
        Rank rank = new Rank(name, data.get(name + ".prefix"), data.getStringList(name + ".permissions"));
        ranks.add(rank);
        return rank;
    }

    public Rank loadRank(Rank r) {
        ranks.add(r);
        return r;
    }

    public Rank getRank(String name) {
       return ranks.stream().filter(rank -> rank.getName().equalsIgnoreCase(name)).findAny().orElse(null);
    }

    public Set<Rank> getRanks() {
        return ranks;
    }

    public Rank isRank(Rank rank) {
        return ranks.stream().filter(r -> r == rank).findAny().orElse(null);
    }

    public Rank getDefaultRank() {
        Rank rank = new Rank("default", "&7&lDefault", new ArrayList<String>());
        saveRank(rank);
        return rank;
    }

    public void saveRank(Rank rank) {
        data.set(rank.getName() + ".prefix", rank.getPrefix());
        data.set(rank.getName() + ".permissions", rank.getPermissions());
    }

    public void removeRank(Rank rank) {
        data.set(rank.getName(), null);
    }

    public void saveRanks() {
        ranks.forEach(this::saveRank);
        core.getLogger().info("SAVED ALL RANKS!");
    }

}
