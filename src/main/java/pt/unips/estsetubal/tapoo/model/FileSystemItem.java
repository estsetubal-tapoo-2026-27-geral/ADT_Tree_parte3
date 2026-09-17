package pt.unips.estsetubal.tapoo.model;

import java.util.Objects;

public final class FileSystemItem {
    public enum Type { FOLDER, FILE }

    private final String name;
    private final Type type;

    public FileSystemItem(String name, Type type) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("O nome não pode estar vazio.");
        this.name = name;
        this.type = Objects.requireNonNull(type, "O tipo não pode ser null.");
    }

    public static FileSystemItem folder(String name) { return new FileSystemItem(name, Type.FOLDER); }
    public static FileSystemItem file(String name) { return new FileSystemItem(name, Type.FILE); }
    public String getName() { return name; }
    public Type getType() { return type; }
    public boolean isFolder() { return type == Type.FOLDER; }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof FileSystemItem other)) return false;
        return name.equals(other.name) && type == other.type;
    }

    @Override
    public int hashCode() { return Objects.hash(name, type); }

    @Override
    public String toString() { return name; }
}
