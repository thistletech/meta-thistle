SUMMARY = "Minimal partner image which includes A/B partition updates"
LICENSE = "MIT"
FILESEXTRAPATHS:prepend := "${THISDIR}/configs:"

require recipes-samples/images/thistle-image-common.inc
# Enable OP-TEE related recipes if provided by the image
require ${@bb.utils.contains('MACHINE_FEATURES', 'optee', 'recipes-samples/images/lmp-feature-optee.inc', '', d)}
require recipes-samples/images/lmp-feature-wifi.inc
require recipes-samples/images/lmp-feature-sbin-path-helper.inc

SOC_DEFAULT_WKS_FILE:portenta-x8 = "imx-ab-boot-image.wks.in"

CORE_IMAGE_BASE_INSTALL_GPLV3 = "\
    packagegroup-core-full-cmdline-utils \
    packagegroup-core-full-cmdline-multiuser \
"

CORE_IMAGE_BASE_INSTALL += " \
    networkmanager-nmcli \
    packagegroup-core-full-cmdline-extended \
    ${@bb.utils.contains('LMP_DISABLE_GPLV3', '1', '', '${CORE_IMAGE_BASE_INSTALL_GPLV3}', d)} \
"

LMP_EXTRA = " \
    lmp-device-tree \
"

ADB = " \
    android-tools \
    android-tools-adbd \
"

ARDUINO = " \
    arduino-ootb \
"

CORE_IMAGE_BASE_INSTALL += " \
    usb-modeswitch \
    mmc-utils \
    ${LMP_EXTRA} \
    ${ADB} \
    ${ARDUINO} \
"