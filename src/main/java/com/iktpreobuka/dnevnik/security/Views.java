package com.iktpreobuka.dnevnik.security;

public class Views {
public static class Ucenik {}
public static class Roditelj extends Ucenik {}
public static class Nastavnik extends Roditelj {}
public static class Admin extends Nastavnik {}
}
