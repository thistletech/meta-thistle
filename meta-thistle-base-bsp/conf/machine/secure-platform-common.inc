fakeroot create_mount_dirs () {
   mkdir -p ${IMAGE_ROOTFS}/mnt/boot
}

IMAGE_PREPROCESS_COMMAND += "create_mount_dirs;"
