FILESEXTRAPATHS:prepend := "${THISDIR}/configs:"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit core-image features_check extrausers

# let's make sure we have a good image..
REQUIRED_DISTRO_FEATURES = " systemd"

# Base packages
CORE_IMAGE_BASE_INSTALL += " \
    lmp-boot-firmware \
    networkmanager \
    resize-helper \
    mtd-utils \
    sudo \
    zram \
    i2c-tools \
"
