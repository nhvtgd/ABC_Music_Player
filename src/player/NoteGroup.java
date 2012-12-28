package player;

/**
 * NoteGroup Interface. Extended by Note, Chord, and Tuplet
 *
 */
public interface NoteGroup {
    public <R> R accept(Visitor<R> v);
}
