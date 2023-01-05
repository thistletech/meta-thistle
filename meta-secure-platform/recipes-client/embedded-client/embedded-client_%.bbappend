do_install:append () {
    if [ -z "${THISTLE_PUBLIC_KEY}" ]; then
        bbfatal "Public key not set"
    fi

    if [ -z "${THISTLE_DEVICE_ENROLLMENT_TOKEN}" ]; then
        bbfatal "Device enrollment token not set"
    fi

    if [ -z "${THISTLE_BOOT_PART_A}" ]; then
        bbfatal "Update client partition A not set"

    fi

    if [ -z "${THISTLE_BOOT_PART_B}" ]; then
        bbfatal "Update client partition B not set"
    fi

    install -d ${D}${sysconfdir}/tuc
    echo "{\n\
    \"persistent_directory\": \"/mnt/boot\",\n\
    \"public_keys\": [\"${THISTLE_PUBLIC_KEY}\"],\n\
    \"bootloader\": \"ThistleUboot\",\n\
    \"part_a\": \"${THISTLE_BOOT_PART_A}\",\n\
    \"part_b\": \"${THISTLE_BOOT_PART_B}\",\n\
    \"name\": \"testdevice\",\n\
    \"device_enrollment_token\": \"${THISTLE_DEVICE_ENROLLMENT_TOKEN}\"\n\
}" > ${D}${sysconfdir}/tuc/config.json
    chmod 0644 ${D}${sysconfdir}/tuc/config.json
}