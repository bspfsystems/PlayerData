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

package org.bspfsystems.playerdata.api.event;

import java.util.UUID;
import org.bspfsystems.pluginevents.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * An {@link Event} that is called when a player joins the game.
 */
public class PlayerJoinEvent extends Event {
    
    public enum JoinType {
        NEW_PLAYER,
        NAME_CHANGE,
        NORMAL;
    }
    
    private final String name;
    private final String oldName;
    private final UUID uniqueId;
    private final JoinType joinType;
    
    /**
     * Called when a player joins the game normally.
     * 
     * @param name The name of the player.
     * @param uniqueId The {@link UUID} of the player.
     * @see PlayerJoinEvent#PlayerJoinEvent(String, String, UUID, JoinType)
     */
    public PlayerJoinEvent(@NotNull final String name, @NotNull final UUID uniqueId) {
        this(name, null, uniqueId, PlayerJoinEvent.JoinType.NORMAL);
    }
    
    /**
     * Called when a player joins the game after a name change.
     * 
     * @param name The (current) name of the player.
     * @param oldName The old name of the player.
     * @param uniqueId The {@link UUID} of the player.
     * @see PlayerJoinEvent#PlayerJoinEvent(String, String, UUID, JoinType)
     */
    public PlayerJoinEvent(@NotNull final String name, @NotNull final String oldName, @NotNull final UUID uniqueId) {
        this(name, oldName, uniqueId, PlayerJoinEvent.JoinType.NAME_CHANGE);
    }
    
    /**
     * Called when a player joins the game.
     * <p>
     * This is the only way to notify that a new player has joined the game.
     * <p>
     * An {@link IllegalArgumentException} will be thrown if
     * <code>oldName</code> is <code>null</code> and <code>joinType</code> is
     * {@link PlayerJoinEvent.JoinType#NAME_CHANGE}, or if <code>oldName</code>
     * is not <code>null</code> and <code>joinType</code> is either of the other
     * options.
     * 
     * @param name The (current) name of the player.
     * @param oldName The old name of the player, or <code>null</code> if the
     *                player joined the game normally or is new.
     * @param uniqueId The {@link UUID} of the player.
     * @param joinType The {@link PlayerJoinEvent.JoinType type} of join the
     *                 player performed.
     * @throws IllegalArgumentException If the combination of
     *                                  <code>oldName</code> and
     *                                  <code>joinType</code> are not
     *                                  compatible.
     */
    public PlayerJoinEvent(@NotNull final String name, @Nullable final String oldName, @NotNull final UUID uniqueId, @NotNull final PlayerJoinEvent.JoinType joinType) throws IllegalArgumentException {
        
        if (oldName == null && joinType == PlayerJoinEvent.JoinType.NAME_CHANGE) {
            throw new IllegalArgumentException("Disallowed combination of oldName and joinType: oldName is null and joinType is NAME_CHANGE.");
        } else if (oldName != null && joinType != PlayerJoinEvent.JoinType.NAME_CHANGE) {
            throw new IllegalArgumentException("Disallowed combination of oldName and joinType: oldName is NOT null and joinType is NOT NAME_CHANGE.");
        }
        
        this.name = name;
        this.oldName = oldName;
        this.uniqueId = uniqueId;
        this.joinType = joinType;
    }
    
    /**
     * Gets the name of the player that joined the game.
     * 
     * @return The name of the player that joined the game.
     */
    @NotNull
    public final String getName() {
        return this.name;
    }
    
    /**
     * Gets the old name of the player that joined the game.
     * <p>
     * This will be <code>null</code> unless
     * {@link PlayerJoinEvent#getJoinType()} returns
     * {@link PlayerJoinEvent.JoinType#NAME_CHANGE}.
     * 
     * @return The old name of the player that joined the name, or
     *         <code>null</code> if the player did not change their
     *         name.
     */
    @Nullable
    public final String getOldName() {
        return this.oldName;
    }
    
    /**
     * Gets the {@link UUID} of the player that joined the game.
     * 
     * @return The {@link UUID} of the player that joined the game.
     */
    @NotNull
    public final UUID getUniqueId() {
        return this.uniqueId;
    }
    
    /**
     * Gets the {@link PlayerJoinEvent.JoinType} for the player that joined the
     * game.
     * 
     * @return The {@link PlayerJoinEvent.JoinType} for the player that joined
     *         the game.
     */
    @NotNull
    public final PlayerJoinEvent.JoinType getJoinType() {
        return this.joinType;
    }
}
