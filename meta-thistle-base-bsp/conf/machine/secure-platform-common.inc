# Create a persistent directory to store any data that
# should persist after an update
fakeroot create_mount_dirs () {
   mkdir -p ${IMAGE_ROOTFS}/mnt/boot
   mkdir -p ${IMAGE_ROOTFS}/mnt/persist
}

IMAGE_PREPROCESS_COMMAND += "create_mount_dirs;"
