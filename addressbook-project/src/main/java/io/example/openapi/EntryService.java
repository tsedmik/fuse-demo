package io.example.openapi;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * A {@link Entry} service for REST operations
 */
public class EntryService {

    // use a tree map so they become sorted
    private final Map<String, Entry> entries = new TreeMap<>();

    public EntryService() {

        // Add some initial data
        entries.put("1", new Entry(1, "John Doe", "+420 123 456 789"));
        entries.put("2", new Entry(2, "Mickey Mouse", "+420 987 654 321"));
    }

    /**
     * Get an entry by the given id
     * 
     * --> GET /entries/{id}
     */
    public Entry getEntry(String id) {
        return entries.get(id);
    }

    /**
     * Get all entries in the address book
     * 
     * --> GET /entries
     */
    public Collection<Entry> getAllEntries() {
        return entries.values();
    }

    /**
     * Add new entry into the address book
     * 
     * --> POST /entries
     */
    public void addEntry(Entry entry) {
        entries.put("" + entry.getId(), entry);
    }

    /**
     * Update an entry in the address book
     * 
     * --> PUT /entries/{id}
     */
    public void updateEntry(Entry entry) {
        entries.replace("" + entry.getId(), entry);
    }

    /**
     * Removes an entry from address book
     * 
     * --> DELETE /entries/{id}
     */
    public void removeEntry(String id) {
        entries.remove(id);
    }
}