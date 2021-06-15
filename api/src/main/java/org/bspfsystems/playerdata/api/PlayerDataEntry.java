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

package org.bspfsystems.playerdata.api;

import java.util.UUID;
import org.bspfsystems.playerdata.api.plugin.PlayerDataPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an object to hold basic player data, including the player's
 * name and their {@link UUID}.
 * <p>
 * Depending on the implementation, the player names stored by the
 * {@link PlayerDataPlugin} may differ. The name may be from when the player
 * last logged into the server, or it may be the most recent if the
 * {@link PlayerDataPlugin} implementation uses the Mojang API to update
 * names.
 */
public interface PlayerDataEntry {
    
    /**
     * Gets the name of the player.
     * 
     * @return The name of the player.
     */
    @NotNull
    String getName();
    
    /**
     * Gets the {@link UUID} of the player.
     * 
     * @return The {@link UUID} of the player.
     */
    @NotNull
    UUID getUniqueId();
}
