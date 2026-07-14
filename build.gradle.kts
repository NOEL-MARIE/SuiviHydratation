
// =================================================================================
// CONFIGURATION DES PLUGINS (RACINE DU PROJET)
// Ici, on définit les outils nécessaires sans passer par le catalogue "libs" manquant.
// =================================================================================

plugins {
    // Plugin de base pour l'application Android (Version stable 8.2.2)
    id("com.android.application") version "8.10.1" apply false

    // Plugin pour le support du langage Kotlin (Version stable 1.9.22)
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
}