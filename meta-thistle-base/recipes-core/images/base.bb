SUMMARY = "Image providing base functionalities"
LICENSE = "MIT"

inherit core-image

# Disable all image features unless explicitily required
IMAGE_FEATURES = ""

IMAGE_INSTALL:append = " networkmanager"

LMP_EXTRA:portenta-x8 = " \
    lmp-device-tree \
"

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

do_build[depends] += "virtual/bootloader:do_deploy"
