SUMMARY = "Image providing base functionalities"
LICENSE = "MIT"

inherit core-image

# Disable all image features unless explicitily required
IMAGE_FEATURES = ""

IMAGE_INSTALL:append = " networkmanager"

CORE_IMAGE_BASE_INSTALL:portenta-x8 += " \
    networkmanager \
    mtd-utils \
    sudo \
    zram \
    i2c-tools \
"

do_build[depends] += "virtual/bootloader:do_deploy"
