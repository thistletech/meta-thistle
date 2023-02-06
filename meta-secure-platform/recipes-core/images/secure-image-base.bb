SUMMARY = "Image providing base functionality for a secure platform"
LICENSE = "MIT"

inherit core-image

# Disable all image features unless explicitily required
IMAGE_FEATURES = ""
TOOLCHAIN_TARGET_TASK:append = " rustls-ffi-dev"
IMAGE_INSTALL:append = " networkmanager"

LMP_EXTRA:portenta-x8 = " \
    lmp-device-tree \
"

# Base packages
CORE_IMAGE_BASE_INSTALL:portenta-x8 += " \
    lmp-boot-firmware \
    networkmanager \
    resize-helper \
    mtd-utils \
    sudo \
    zram \
    i2c-tools \
    ${LMP_EXTRA} \
"
# Make sure to build uboot
do_build[depends] += "virtual/bootloader:do_deploy"
