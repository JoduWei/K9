package com.tterrag.k9.commands.api;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tterrag.k9.util.DefaultNonNull;
import com.tterrag.k9.util.NullHelper;
import com.tterrag.k9.util.Requirements;

@DefaultNonNull
public interface ICommand {

	String getName();
	
	boolean admin();
	
	void process(CommandContext ctx) throws CommandException;
	
	Collection<Flag> getFlags();
	
	List<Argument<?>> getArguments();
	
	String getDescription();
	
	/**
	 * @return A map of permissions to their requirement type. Recommended to use {@link EnumMap} for performance.
	 */
    default Requirements requirements() {
	    return Requirements.none();
	}
	
	default void gatherParsers(GsonBuilder builder) {}

	/**
	 * Use this if this command is only a proxy to register children commands.
	 */
	default boolean isTransient() {
	    return false;
	}
	
	/**
	 * A set of commands to be registered at the time this command is registered. Use this for special constructors.
	 */
    default Iterable<ICommand> getChildren() {
	    return NullHelper.notnullJ(Collections.emptyList(), "Collections#emptyList");
	}
	
	/* == Event Hooks == */

    default void onRegister() {}
    
    default void init(File dataFolder, Gson gson) {}
    
    default void save(File dataFolder, Gson gson) {}
    
    default void onUnregister() {}
    
    default void onShutdown() {}

}
