# DupeZ

A Fabric mod for Minecraft 1.21.1 that allows item duplication via a command.

## Features

- `/dupe <amount>` - Duplicate the item in your main hand
- Owner-only access (UUID-based authentication)
- Support for amounts 1-64

## Installation

1. Download the latest release JAR
2. Place it in your `mods` folder
3. Make sure you have Fabric Loader and Fabric API installed

## Usage

```
/dupe <amount>
```

Example:
```
/dupe 32
```

## Building from Source

```bash
./gradlew build
```

The mod JAR will be in `build/libs/`

## License

MIT
