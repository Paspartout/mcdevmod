# McDevMod

This is a mod that should help me accelerate mod development and also serves as a playground.
It works on both fabric and forge thanks to [architectury].
Don't expect the code in here to be clean.

## Features

- [LazyDFU] that works on fabric and forge to speedup starting the client
- Client starts directly to first world in wordlist
- Small Debug UI 
  - to quickly resize window to 720p/1080p for screen recordings
  - to run other commands quickly without typing them

## Ideas/TODO

- Port /tick command from [fabric-carpet] to be available on forge and fabric
  - Provide UI/KeyBinds to freeze/step ticks (Hope this might be helpful to debug entities and such)
- Wheel UI to quickly select stuff?

- Display useful debug information similar to [MiniHUD]
  - Show Entity information as HUD?
  - Add option to toggle vanilla debug renderers similar to [MiniHUD]
    - May need change to GPL/LGPL License
- Maybe remove fabric-api dependency? Would that benefit startup times much?

## Credits

- [LazyDFU] by Andrew Steinborn licensed under MIT(see LICENSE.lazydfu)
- [architectury] by shedaniel

[LazyDFU]: https://github.com/astei/lazydfu
[architectury]: https://github.com/architectury/architectury-plugin
[fabric-carpet]: https://github.com/gnembon/fabric-carpet
[MiniHUD]: https://github.com/maruohon/minihud