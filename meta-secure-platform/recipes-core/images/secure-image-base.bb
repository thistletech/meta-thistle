SUMMARY = "Image providing base functionality for a secure platform"
LICENSE = "MIT"

inherit core-image

# Disable all image features unless explicitily required
IMAGE_FEATURES = ""
TOOLCHAIN_TARGET_TASK:append = " rustls-ffi-dev"
IMAGE_INSTALL:append = " networkmanager"

# Make sure to build uboot
do_build[depends] += "virtual/bootloader:do_deploy"
