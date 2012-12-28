package player;

public interface Visitor<R> {
    public R on(Note n);
    public R on(Chord c);
    public R on(Tuplet t);
}
