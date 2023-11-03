# Cairo Language Support

<div align="center">
<figure>
<img src="./src/main/resources/icons/cairo.svg" width="400">
</figure>
<br>
</div>

![GitHub](https://img.shields.io/github/license/kahondev/cairo-jetbrains)

> 🚧 This was made for my own pressing need as a JetBrains user and budding Cairo programmer. I'm not an expert JetBrains plugin developer nor Kotlin programmer. That was a lot of words to say "this plugin kinda sorta works".

The Cairo language plugin for JetBrains products. This uses the [Cairo Language Server](https://github.com/starkware-libs/cairo/blob/main/vscode-cairo/README.md#install-the-cairo-language-server).

*Pyramid Emoji by Niklas Kuntz. From [OpenMoji](https://openmoji.org/library/emoji-E20F/).*

## Requirements

- This is only available for **`233.* EAP` products** of JetBrains. You can read more about EAP [here](https://www.jetbrains.com/resources/eap/).
- Users who have a JetBrains subscription. More information about why [here](https://blog.jetbrains.com/platform/2023/07/lsp-for-plugin-developers/).

## Installation

### From Disk

1. Install [Scarb](https://docs.swmansion.com/scarb/download.html#install-via-installation-script).
2. Clone this repository:
```sh
git clone https://github.com/kasteph/cairo-language-support
```
3. Run `buildPlugin` from Gradle.
4. Run `Install Plugin from Disk...` from your JetBrains IDE.
5. Select the Cairo build artifact (a JAR file).

### From Marketplace

🚧 coming soon!

## Known Issues

The shortcut to make a comment does not work.
[Upvote the issue on the JetBrains issue tracker here](https://youtrack.jetbrains.com/issue/IDEA-337134/Provide-API-for-plugin-developers-to-implement-the-Comment-Line-action-without-writing-lexer-parser).
