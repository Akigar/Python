import numpy as np
import matplotlib.pyplot as plt

from skimage.measure import regionprops, label
from skimage.morphology import erosion, dilation

def count_lakes_and_bays(region):
    symbol = ~region.image
    lb = label(symbol)
    reg = regionprops(lb)
    lakes = 0
    bays = 0
    for reg in regionprops(lb):
        is_lake = True
        for y, x in reg.coords:
            if (y == 0 or x == 0 or y == region.image.shape[0] - 1 or x == region.image.shape[1] - 1):
                is_lake = False
                break
        lakes += is_lake
        bays += not is_lake
    return lakes, bays

def has_vline(image):
    return 1 in erosion(np.mean(image, 0), [1,1,1])

def has_hine(image):
    return 1 in np.mean(image, 1)

def recognize(im_region):
    lakes, bays = count_lakes_and_bays(im_region)
    if lakes == 2:
        if has_vline(im_region.image):
            return 'B'
        else:
            return '8'
    elif lakes == 1: #A or 0
        if bays == 4:
            return "0"
        elif bays == 3:
            return "A"
        elif (im_region.perimeter ** 2) / im_region.area < 59:
            return "P"
        else:
            return "D"
    elif lakes == 0:
        if np.mean(im_region.image) == 1.0:
            return '-'
        elif has_vline(im_region.image):
            return '1'
        elif bays == 2:
            return '/'
        elif has_hine(im_region.image):
            return '*'
        elif bays == 4:
            return 'X'
        #elif bays
        else:
            return 'W'

im = plt.imread("symbols.png")
im = np.mean(im, 2)
im[im > 0] = 1

lb = label(im)
regions = regionprops(lb)
result = {}
#print(count_lakes_and_bays(regions[50]))

for reg in regions:
    symbol = recognize(reg)
    if symbol not in result:
        result[symbol] = 0
    result[symbol] += 1
print(result)
#print(recognize(regions[8])) #1 - B, 3 - 8

#plt.imshow(regions[8].image)
print(np.max(lb) == sum(result.values()))
plt.show()
