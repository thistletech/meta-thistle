# meta-thistle

This repository contains publicly available layers for building and testing the
Thistle update client within a Yocto image. Currently only the Kirkstone(4.0)
release of Yocto is supported.

## Layers

The following layers are included:

- `meta-secure-platform` - A layer for creating a basic secure image.
- `meta-update-client-local` - A layer for installing the update client.
- `meta-lmp-extension` - A layer to build in security features on top of the linux microPlatform from foundaries.io.
- `meta-raspberrypi` - A layer to allow the secure platform to be run on a Raspberry Pi.


