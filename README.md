<div align="center">
# 💧 Suivi d'Hydratation

**Application Android native pour suivre votre consommation d'eau quotidienne**

Développée avec Kotlin et Jetpack Compose (Material 3)

---
</div>

## 📖 À propos

**Suivi d'Hydratation** est une application mobile Android conçue pour aider les utilisateurs à suivre et maintenir une consommation d'eau saine tout au long de la journée. L'application propose une interface moderne et intuitive basée sur Material Design 3.

## ✨ Fonctionnalités

- 💧 Suivi en temps réel de la consommation d'eau quotidienne
- 🎨 Interface moderne et fluide avec Jetpack Compose (Material 3)
- ↩️ Annulation de la dernière action (Undo)
- 🔄 Réinitialisation du compteur journalier
- 📱 Design adaptatif et responsive

## 🛠️ Stack technique

| Catégorie | Technologie |
|---|---|
| Langage | Kotlin |
| Interface utilisateur | Jetpack Compose · Material 3 |
| Architecture | MVVM (`ViewModel`) |
| Système de build | Gradle (Kotlin DSL) |
| Gestion des icônes | Compose Material Icons Extended |
| JDK | 17 |
| SDK cible | API 34 |
| SDK minimum | API 24 |

## 📂 Structure du projet

```
suivi-hydratation-android/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/degonzague/hydration/
│   │       │   ├── MainActivity.kt
│   │       │   └── HydrationApp.kt
│   │       ├── res/
│   │       │   ├── values/
│   │       │   │   ├── themes.xml
│   │       │   │   └── colors.xml
│   │       │   ├── mipmap-anydpi-v26/
│   │       │   └── drawable/
│   │       └── AndroidManifest.xml
│   └── build.gradle.kts
├── gradle.properties
├── settings.gradle.kts
└── build.gradle.kts
```

## 🚀 Installation

### Prérequis

- [Android Studio](https://developer.android.com/studio) (dernière version stable recommandée)
- JDK 17
- Un appareil Android ou un émulateur (API 24 minimum)

### Étapes

1. **Cloner le repository**
   ```bash
   git clone https://github.com/NOEL-MARIE/suivi-hydratation-android.git
   ```

2. **Ouvrir le projet**
   Lancer Android Studio → `File` → `Open` → sélectionner le dossier cloné

3. **Synchroniser Gradle**
   Cliquer sur **Sync Now** dans le bandeau qui apparaît (le wrapper télécharge automatiquement Gradle 8.14.5)

4. **Lancer l'application**
   Sélectionner un appareil/émulateur puis cliquer sur ▶️ **Run**

## ⚙️ Configuration principale

Extrait des dépendances clés du module `app` :

```kotlin
dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("com.google.android.material:material:1.12.0")

    val composeBom = platform("androidx.compose:compose-bom:2024.02.00")
    implementation(composeBom)
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")
}
```

## 🗺️ Feuille de route

- [ ] Historique et statistiques de consommation
- [ ] Notifications de rappel d'hydratation
- [ ] Mode sombre personnalisable
- [ ] Sauvegarde des données (Room / DataStore)

## 🤝 Contribuer

Les contributions sont les bienvenues ! Pour proposer une modification :

1. Forker le projet
2. Créer une branche (`git checkout -b feature/ma-fonctionnalite`)
3. Committer les changements (`git commit -m "feat: ajout de ma fonctionnalité"`)
4. Pousser la branche (`git push origin feature/ma-fonctionnalite`)
5. Ouvrir une Pull Request

## 📄 Licence

Ce projet est distribué sous licence MIT. Voir le fichier `LICENSE` pour plus de détails.

## 👤 Auteur

**DE GONZAGUE **
Étudiant en Licence Professionnelle Réseaux & Génie Logiciel (LPRGL3)
Pigier Côte d'Ivoire

[![GitHub](https://img.shields.io/badge/GitHub-NOEL--MARIE-181717?style=flat&logo=github)](https://github.com/NOEL-MARIE)

---

<div align="center">

*Fait avec 💧 et Jetpack Compose*

</div>
