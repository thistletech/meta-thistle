# meta-thistle

This repository contains publicly available layers for building and testing the
Thistle update client within a Yocto image. Currently only the Kirkstone(4.0)
release of Yocto is supported.

## Layers
The following layers are included:
- `meta-thistle-base` - A layer for creating a base image.
- `meta-thistle-base-bsp` - A layer adding support for certain platforms (rpi4, qemuarm64) as well as additional software.
- `meta-update-client` - A layer for installing the Thistle update client.

## meta-thistle-base
This layers provides the [thistle-base](https://github.com/thistletech/meta-thistle/blob/main/meta-thistle-base/conf/distro/thistle-base.conf) distribution.
This distibutio comes with a suite of sane defaults (vfat support, networkmanager, bash profile, sudo, i2c tools). In addition it also optionally provides `curl`.

## meta-thistle-base-bsp
This layers provides additional support for [extended platforms](https://github.com/thistletech/meta-thistle/blob/main/meta-thistle-base-bsp/conf/machine). It also provides recipe for building and integrating with u-boot.

## meta-update-client
This layers provides the [Thistle Update Client](https://docs.thistle.tech/update_client/overview) binary for the target platform
