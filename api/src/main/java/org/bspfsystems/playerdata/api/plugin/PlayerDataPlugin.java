/*
 * This file is part of the PlayerData plugins for
 * BungeeCord and Bukkit servers for Minecraft.
 *
 * Copyright 2020-2021 BSPF Systems, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bspfsystems.playerdata.api.plugin;

import java.io.File;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;
import org.bspfsystems.playerdata.api.PlayerDataEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The interface that allows access to the player data functions. The plugin
 * will contain mappings between player names and {@link UUID}s.
 * <p>
 * Depending on the implementation, those mappings may be populated via various
 * methods. Some implementations will only map the players that have logged into
 * the BungeeCord and/or Bukkit instance(s). Others may query the Mojang API for
 * updates to player data.
 */
public interface PlayerDataPlugin {
    
    /**
     * Gets the {@link UUID} for the given player name, or <code>null</code> if
     * the name is not known, depending on the implementation.
     * 
     * @param name The name of the player.
     * @return The {@link UUID} of the player, or <code>null</code> if one
     *         cannot be found.
     */
    @Nullable
    UUID getUniqueId(@NotNull String name);
    
    /**
     * Gets the player name for the given {@link UUID}, or <code>null</code> if
     * the {@link UUID} is not known, depending on the implementation.
     * 
     * @param uniqueId The {@link UUID} of the player.
     * @return The player name, or <code>null</code> if one cannot be found.
     */
    @Nullable
    String getName(@NotNull UUID uniqueId);
    
    /**
     * Gets a {@link Set} of any player names that match. Depending on the
     * implementation, this may be a "starts with" matching, or more advanced
     * matching, including letter swaps and other misspellings.
     * 
     * @param name The (partial) player name to match.
     * @return A {@link Set} containing any matching names.
     */
    @NotNull
    Set<String> getMatchingNames(@NotNull String name);
    
    /**
     * Gets the {@link PlayerDataEntry} for the player with the given name, or
     * <code>null</code> if the name is not known, depending on the
     * implementation.
     * 
     * @param name The name of the player.
     * @return The {@link PlayerDataEntry} for the given player name, or
     *         <code>null</code> if one cannot be found.
     */
    @Nullable
    PlayerDataEntry getEntry(@NotNull String name);
    
    /**
     * Gets the {@link PlayerDataEntry} for the player with the given
     * {@link UUID}, or <code>null</code> if the {@link UUID} is not known,
     * depending on the implementation.
     * 
     * @param uniqueId The {@link UUID} of the player.
     * @return The {@link PlayerDataEntry} for the given {@link UUID}, or
     *         <code>null</code> if one cannot be found.
     */
    @Nullable
    PlayerDataEntry getEntry(@NotNull UUID uniqueId);
    
    /**
     * Gets a {@link Set} of any {@link PlayerDataEntry PlayerDataEntries} where
     * the player name matches. Depending on the implementation, this may be a
     * "starts with" matching, or more advanced matching, including letter swaps
     * and other misspellings.
     * 
     * @param name The (partial} player name to match.
     * @return A {@link Set} containing all
     *         {@link PlayerDataEntry PlayerDataEntries} that have matching
     *         names.
     */
    @NotNull
    Set<PlayerDataEntry> getMatchingEntries(@NotNull String name);
    
    /**
     * Gets the {@link Set} of all player names that are known. The data in the
     * set may vary depending on the implementation.
     * 
     * @return The {@link Set} of all known player names.
     */
    @NotNull
    Set<String> getAllNames();
    
    /**
     * Gets the {@link Set} of all player {@link UUID}s that are known. The data
     * in the set may vary depending on the implementation.
     * 
     * @return The {@link Set} of all known player {@link UUID}s.
     */
    @NotNull
    Set<UUID> getAllUniqueIds();
    
    /**
     * Gets the {@link Set} of all {@link PlayerDataEntry PlayerDataEntries}
     * that are known. The data in the set may vary depending on the
     * implementation.
     * 
     * @return The {@link Set} of all known
     *         {@link PlayerDataEntry PlayerDataEntries}.
     */
    @NotNull
    Set<PlayerDataEntry> getAllEntries();
    
    /**
     * Gets the {@link Logger} used by this {@link PlayerDataPlugin}.
     * 
     * @return The {@link Logger} used by this {@link PlayerDataPlugin}.
     */
    @NotNull
    Logger getLogger();
    
    /**
     * Gets the directory where any data files are stored for this
     * {@link PlayerDataPlugin}.
     * 
     * @return The directory where any data files are stored for this
     *         {@link PlayerDataPlugin}.
     */
    @NotNull
    File getDataDirectory();
}
